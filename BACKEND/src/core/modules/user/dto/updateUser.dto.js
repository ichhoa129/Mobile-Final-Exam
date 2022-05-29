import { ApiDocument } from 'core/config/swagger.config';
import { SwaggerDocument } from 'packages/swagger';

ApiDocument.addModel('UpdateUserDto', {
    username: SwaggerDocument.ApiProperty({ type: 'string' }),
});

export const UpdateUserDto = body => ({
    username: body.username,
});
