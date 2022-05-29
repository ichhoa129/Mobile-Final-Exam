import { model, Schema } from 'mongoose';
import { extendBaseModel } from '../../infrastructure/model';

const COLOR_REGEX = /^#([0-9a-f]{3}){1,2}$/i;

const schema = extendBaseModel({
    name: {
        type: String,
        require: [true, 'Role name is empty'],
    },
    color: {
        type: String,
        validate: COLOR_REGEX,
    },
    server: {
        type: Schema.Types.ObjectId,
        ref: 'servers',
    },
    permissions: [{
        type: Schema.Types.ObjectId,
        ref: 'permissions',
    }]
});

export const RoleModel = model('roles', schema);
