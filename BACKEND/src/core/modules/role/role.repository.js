import { DataRepository } from '../../../packages/restBuilder/core/dataHandler';
import { RoleModel } from './role.model';

class Repository extends DataRepository {
    constructor() {
        super(RoleModel);
    }
}

export const RoleRepository = new Repository();
