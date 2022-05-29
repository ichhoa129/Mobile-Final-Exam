import { DATABASE_URL } from 'core/env';
import mongoose from 'mongoose';
import { MongooseProvider } from '../modules/mongoose/mongoose.provider';

export const DatabaseInstance = MongooseProvider
    .builder()
    .setConnectionString(DATABASE_URL)
    .setMongooseInstance(mongoose);
