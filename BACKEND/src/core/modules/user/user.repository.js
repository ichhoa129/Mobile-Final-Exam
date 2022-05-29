/* eslint-disable func-names */
import { UserModel } from 'core/modules/user/user.model';
import { DataRepository } from 'packages/restBuilder/core/dataHandler/data.repository';

class Repository extends DataRepository {
    constructor() {
        super(UserModel);
    }

    findByEmail(email) {
        return this.model.findOne({ email }).lean();
    }

    findDetailById(id) {
        return this.model.findById(id,
            ['_id', 'email', 'username'],
            { timestamps: true });
    }
}

export const UserRepository = new Repository();
