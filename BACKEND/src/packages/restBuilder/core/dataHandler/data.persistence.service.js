import { merge } from 'lodash';
import { InternalServerException } from 'packages/httpException';
import { logger } from 'packages/logger';
import { DataRepository } from './data.repository';
import { documentCleanerVisitor } from './document-cleaner.visitor';

export class DataPersistenceService {
    repository;

    constructor(repository) {
        if (!(repository instanceof DataRepository)) {
            throw new Error('Extended class DataPersistenceService should be constructed with a DataRepository instance');
        }
        this.repository = repository;
    }

    /**
     * @param {any} dto
     * @param {() => typeof import('packages/httpException/HttpException').HttpException} exceptionDealingWithDatabaseError
     * @returns
     */
    async createOneSafety(dto, exceptionDealingWithDatabaseError = new InternalServerException()) {
        let createdData;

        try {
            createdData = await this.repository.model.create(dto);
        } catch (e) {
            logger.error(e.message);
            logger.error(e.stack);
            throw exceptionDealingWithDatabaseError();
        }

        return createdData;
    }

    /**
     * @param {any} id
     * @param {Record<any, any>} sourceDocument
     * @param {Record<any, any>} updateDocument
     */
    async patchOne(id, sourceDocument, updateDocument) {
        documentCleanerVisitor(updateDocument);
        const updateDoc = merge(sourceDocument, updateDocument);
        await this.repository.model.updateOne(
            {
                _id: id,
            },
            updateDoc,
        );
    }

    /**
     * @param {any} id
     * @param {() => typeof import('packages/httpException/HttpException').HttpException} notFoundRecordException
     * @returns
     */
    async softDeleteById(id, notFoundRecordException) {
        let isDeleted;
        try {
            isDeleted = await this.repository.softDeleteById(id);
        } catch (e) {
            logger.error(e.message);
            logger.error(e.stack);
        }
        if (!isDeleted) {
            throw notFoundRecordException();
        }
        return isDeleted;
    }
}
