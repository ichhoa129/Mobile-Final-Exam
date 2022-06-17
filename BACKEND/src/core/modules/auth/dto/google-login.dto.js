import { ApiDocument } from 'core/config/swagger.config';
import { SwaggerDocument } from 'packages/swagger';

ApiDocument.addModel('GoogleLoginDto',
    {
        token: SwaggerDocument.ApiProperty({ type: 'string' })
    });

export const GoogleLoginDto = body => ({
    token: body.token
});
