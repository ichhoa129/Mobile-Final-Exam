import { extendBaseModel } from 'core/infrastructure/model';
import { model } from 'mongoose';

const schema = extendBaseModel({
    alt: {
        type: String,
    },
    description: {
        type: String,
    },
    created_at: {
        type: Date,
    },
    height: {
        type: Number,
    },
    width: {
        type: Number,
    },
    tags: {
        type: [String],
    },
    title: { type: String },
    slug: { type: String },
    urls: {
        small: { type: String },
        medium: { type: String },
        large: { type: String },
        download: { type: String },
        download_link: { type: String },
    }
});

export const ImageModel = model('images', schema);
