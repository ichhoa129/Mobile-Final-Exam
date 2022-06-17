import { ApiDocument } from 'core/config/swagger.config';
import { SwaggerDocument } from 'packages/swagger';

ApiDocument.addModel('CreateUserWithGoogleDto', {
    email: SwaggerDocument.ApiProperty({ type: 'string' }),
    username: SwaggerDocument.ApiProperty({ type: 'string' }),
});

export const CreateUserWithGoogleDto = body => ({
    email: body.email,
    full_name: body.name,
});
