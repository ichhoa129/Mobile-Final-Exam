import Joi from 'joi';
import { DefaultValidatorInterceptor } from 'core/infrastructure/interceptor';
import { JoiUtils } from '../../../utils';

export const UpdateUserInterceptor = new DefaultValidatorInterceptor(
    Joi.object({
        username: JoiUtils.requiredString().min(1),
    })
);
