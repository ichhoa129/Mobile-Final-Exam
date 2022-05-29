/* eslint-disable func-names */
import { ImageModel } from 'core/modules/image/image.model';
import { DataRepository } from 'packages/restBuilder/core/dataHandler/data.repository';

class Repository extends DataRepository {
    constructor() {
        super(ImageModel);
    }

    async findAll(page, size) {
        return this.model
            .find({})
            .skip((page - 1) * size)
            .limit(size);
    }

    findByEmail(email) {
        return this.model.findOne({ email }).lean();
    }

    findDetailById(id) {
        return this.model.findById(id, ['_id', 'email', 'username'], { timestamps: true });
    }

    count() {
        return this.model.countDocuments();
    }
}

export const ImageRepository = new Repository();
