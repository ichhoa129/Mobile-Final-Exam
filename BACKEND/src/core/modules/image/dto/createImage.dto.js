import { ApiDocument } from 'core/config/swagger.config';
import { SwaggerDocument } from 'packages/swagger';

ApiDocument.addModel('CreateImageDto', {
    email: SwaggerDocument.ApiProperty({ type: 'string' }),
    username: SwaggerDocument.ApiProperty({ type: 'string' }),
    password: SwaggerDocument.ApiProperty({ type: 'string' }),
    confirmPassword: SwaggerDocument.ApiProperty({ type: 'string' }),
});

export const CreateImageDto = body => ({
    email: body.email,
    username: body.username,
    password: body.password,
    confirmPassword: body.confirmPassword,
});
