import { Role } from 'core/common/enum';
import { extendBaseModel } from 'core/infrastructure/model';
import { model } from 'mongoose';

const schema = extendBaseModel({
    email: {
        type: String,
        unique: true,
        lowercase: true,
        required: [true, 'User email is empty'],
    },
    username: { type: String },
    password: { type: String },
    roles: {
        type: Array,
        default: [Role.USER],
        schema: { type: String, enum: Object.values(Role) },
    },
});

export const UserModel = model('users', schema);
