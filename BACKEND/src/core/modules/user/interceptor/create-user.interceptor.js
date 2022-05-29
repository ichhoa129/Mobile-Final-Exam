import Joi from 'joi';
import { DefaultValidatorInterceptor } from 'core/infrastructure/interceptor';
import { JoiUtils } from '../../../utils';

export const CreateUserInterceptor = new DefaultValidatorInterceptor(
    Joi.object({
        email: JoiUtils.email().required(),
        username: JoiUtils.requiredString().min(1),
        password: JoiUtils.password().required(),
        confirmPassword: JoiUtils.requiredString(),
    })
);
