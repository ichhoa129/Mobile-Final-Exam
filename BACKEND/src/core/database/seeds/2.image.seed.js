/* eslint-disable import/no-dynamic-require */
/* eslint-disable global-require */
/* eslint-disable import/no-extraneous-dependencies */
import { ImageModel } from 'core/modules/image/image.model';
import path from 'path';

export class ImageSeeder {
    static async run() {
        const data = require(path.join(__dirname, '../db.json'));
        const mappedData = data.map(item => ({
            alt: item.alt,
            description: item.description,
            created_at: item.created_at,
            height: item.height,
            width: item.width,
            tags: item.tags,
            title: item.title,
            slug: item.slug,
            urls: item.image,
        }));

        await ImageModel.deleteMany({});
        await ImageModel.insertMany(mappedData);
    }
}
