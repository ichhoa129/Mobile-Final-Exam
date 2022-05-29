import Joi from 'joi';
import { DefaultValidatorInterceptor } from 'core/infrastructure/interceptor';

export const PaginationInterceptor = new DefaultValidatorInterceptor(
    Joi.object({
        size: Joi.number().integer().min(1).max(30)
            .default(24),
        page: Joi.number().integer().min(1),
    }),
);
