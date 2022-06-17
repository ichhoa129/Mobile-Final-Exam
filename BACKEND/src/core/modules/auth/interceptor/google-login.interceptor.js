import { DefaultValidatorInterceptor } from 'core/infrastructure/interceptor';
import { JoiUtils } from 'core/utils';
import Joi from 'joi';

export const GoogleLoginInterceptor = new DefaultValidatorInterceptor(
    Joi.object({
        token: JoiUtils.requiredString(),
    })
);
