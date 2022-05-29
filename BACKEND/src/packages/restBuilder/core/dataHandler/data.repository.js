import { UnsupportedMethodException } from 'core/infrastructure/exceptions/unsupported-method.exception';
import { logger } from 'packages/logger';
import { camelCase, upperFirst } from 'lodash';

export class DataRepository {
    /**
     * @type {import('mongoose').Model<Document<any, {}>, {}>}
     */
    model;

    constructor(model) {
        this.model = model;
        this.collection = model.collection.collectionName;
        logger.info(
            `[${upperFirst(camelCase(model.collection.collectionName))}Repository] is bundling`,
        );
    }

    /**
    * =======================================================================
    * ==============       Shortcut of model method           ===============
    * =======================================================================
    */

    find(query = {}, fields = []) {
        return this.model.find(query).select(fields).lean();
    }

    findByIds(ids, fields = []) {
        return this.model
            .find({ _id: { $in: ids } })
            .select(fields)
            .lean();
    }

    findOne(condition, fields = []) {
        return this.model.findOne(condition, fields).lean();
    }

    findById(id, fields = []) {
        return this.model.findById(id, fields).lean();
    }

    updateById(id, payload) {
        return this.model.findByIdAndUpdate(id, payload, { new: true });
    }

    updateByIds(ids, fields) {
        return this.model.updateMany(
            { _id: { $in: ids } },
            { ...fields }
        );
    }

    softDeleteById(id) {
        if (!this.model.schema.obj.deletedAt) {
            throw new UnsupportedMethodException(
                this.collection,
                'soft delete',
            );
        }
        return this.updateById(id, { deletedAt: new Date() });
    }

    deleteMany(conditions, options = {}) {
        if (this.model.schema.obj.deletedAt) {
            return this.model.updateMany(conditions, { deletedAt: new Date() });
        }
        return this.model.deleteMany(conditions, options);
    }

    hasRecord(field, value, filter = {}) {
        return this.model.countDocuments({
            [field]: value,
            ...filter
        });
    }
}
