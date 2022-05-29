import { DataPersistenceService } from '../../../packages/restBuilder/core/dataHandler';
import { RoleRepository } from './role.repository';
import { logger } from '../../../packages/logger';
import { Optional } from '../../utils';
import { NotFoundException } from '../../../packages/httpException';
import { ServerRepository } from '../server/server.repository';
import { PermissionRepository } from '../permission/permission.respository';

class Service extends DataPersistenceService {
    constructor() {
        super(RoleRepository);
        this.serverRepository = ServerRepository;
        this.permissionRespository = PermissionRepository;
        this.logger = logger;
    }

    async createOne(roleDto) {
        Optional
            .of(await this.serverRepository.findById(roleDto.server))
            .throwIfNullable(new NotFoundException('Server not found'));
        await Promise.all(roleDto.permissions.map(async permissionId => {
            Optional
                .of(await this.permissionRespository.findById(permissionId))
                .throwIfNullable(new NotFoundException('Permission not found'));
        }));
        return this.createOneSafety(roleDto);
    }
}
export const RoleService = new Service();
