import { ApiDocument } from 'core/config/swagger.config';
import { SwaggerDocument } from 'packages/swagger';

ApiDocument.addModel('UpdateImageDto', {
    username: SwaggerDocument.ApiProperty({ type: 'string' }),
});

export const UpdateImageDto = body => ({
    username: body.username,
});
