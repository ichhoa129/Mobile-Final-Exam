import { Module } from 'packages/handler/Module';
import { CreateUserInterceptor, UpdateUserInterceptor } from 'core/modules/user/interceptor';
import { ObjectId } from 'core/common/swagger';
import { objectIdInterceptor } from 'core/modules/mongoose/objectId.interceptor';
import { hasAdminOrSuperAdminRole, hasAdminRole } from 'core/modules/auth/guard';
import { UserController } from './user.controller';

export const UserResolver = Module.builder()
    .addPrefix({
        prefixPath: '/users',
        tag: 'users',
        module: 'UserModule',
    })
    .register([
        {
            route: '/',
            method: 'put',
            interceptors: [UpdateUserInterceptor],
            body: 'UpdateUserDto',
            guards: [hasAdminRole],
            controller: UserController.updateOne,
            preAuthorization: true,
        },
        {
            route: '/',
            method: 'post',
            interceptors: [CreateUserInterceptor],
            body: 'CreateUserDto',
            controller: UserController.createOne,
            preAuthorization: false,
        },
        {
            route: '/:id',
            method: 'get',
            params: [ObjectId],
            interceptors: [objectIdInterceptor],
            guards: [hasAdminOrSuperAdminRole],
            controller: UserController.findById,
            preAuthorization: true,
        },
    ]);
