import { DataPersistenceService } from 'packages/restBuilder/core/dataHandler/data.persistence.service';
import { documentCleanerVisitor } from 'packages/restBuilder/core/dataHandler/document-cleaner.visitor';
import { mapToModelByUserCreationDto } from 'core/modules/user/mapper/user.mapper';
import { BcryptService } from 'core/modules/auth';
import { Optional } from '../../utils';
import { NotFoundException, DuplicateException, BadRequestException } from '../../../packages/httpException';
import { UserRepository } from './user.repository';

class Service extends DataPersistenceService {
    constructor() {
        super(UserRepository);
        this.bcryptService = BcryptService;
    }

    async upsertOne(userDto, id) {
        if (id) {
            Optional.of(await this.repository.findById(id)).throwIfNullable(new NotFoundException('User not found'));
            return this.repository.updateById(id, userDto);
        }

        Optional.of(await this.repository.findByEmail(userDto.email)).throwIfPresent(new NotFoundException('User already exists'));

        return this.createOneSafety(userDto);
    }

    async createOne(createUserDto) {
        Optional.of(await this.repository.findByEmail(createUserDto.email)).throwIfPresent(new DuplicateException('Email is being used'));

        if (createUserDto.password !== createUserDto.confirmPassword) {
            throw new BadRequestException('Password does not match');
        }
        createUserDto.password = this.bcryptService.hash(createUserDto.password);

        const mappedToModel = documentCleanerVisitor(mapToModelByUserCreationDto(createUserDto));

        const createdUser = await this.createOneSafety(mappedToModel);

        return { _id: createdUser._id };
    }

    async findById(id) {
        return Optional.of(await this.repository.findDetailById(id))
            .throwIfNotPresent(new NotFoundException())
            .get();
    }
}

export const UserService = new Service();
