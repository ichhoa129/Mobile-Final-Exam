import { Module } from 'packages/handler/Module';
// import { CreateUserInterceptor, UpdateUserInterceptor } from 'core/modules/user/interceptor';
import { QueryCriteriaDocument } from 'core/common/swagger';
// import { objectIdInterceptor } from 'core/modules/mongoose/objectId.interceptor';
// import { hasAdminOrSuperAdminRole, hasAdminRole } from 'core/modules/auth/guard';
import { PaginationInterceptor } from 'core/modules/interceptor/filter/pagination.interceptor';
import { ImageController } from './image.controller';

export const ImageResolver = Module.builder()
    .addPrefix({
        prefixPath: '/images',
        tag: 'images',
        module: 'ImageModule',
    })
    .register([
        // {
        //     route: '/',
        //     method: 'put',
        //     interceptors: [UpdateUserInterceptor],
        //     body: 'UpdateUserDto',
        //     guards: [hasAdminRole],
        //     controller: ImageController.updateOne,
        //     preAuthorization: true,
        // },
        // {
        //     route: '/',
        //     method: 'post',
        //     interceptors: [CreateUserInterceptor],
        //     body: 'CreateUserDto',
        //     controller: ImageController.createOne,
        //     preAuthorization: false,
        // },
        // {
        //     route: '/:id',
        //     method: 'get',
        //     params: [ObjectId],
        //     interceptors: [objectIdInterceptor],
        //     guards: [hasAdminOrSuperAdminRole],
        //     controller: ImageController.findById,
        //     preAuthorization: true,
        // },
        {
            route: '/',
            method: 'get',
            params: [
                QueryCriteriaDocument.page,
                QueryCriteriaDocument.size
            ],
            interceptors: [PaginationInterceptor],
            controller: ImageController.findAll,
        },
    ]);
