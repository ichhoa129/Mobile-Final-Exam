/* eslint-disable no-restricted-syntax */
/* eslint-disable no-await-in-loop */
/* eslint-disable global-require */
/* eslint-disable import/no-dynamic-require */
import path from 'path';
import { glob } from 'glob';
import mongoose from 'mongoose';
import { logger } from 'packages/logger';
import { serial } from 'packages/taskExecution';
import dbConfig from '../../../mongodb.config.json';

(async () => {
    try {
        await mongoose.connect(dbConfig.connectionString, {
            useNewUrlParser: true,
            useUnifiedTopology: true,
        });
        logger.info('Database connected successfully');

        const filePaths = glob.sync(path.join(process.cwd(), 'src/core/database/seeds/*'));
        const tasks = [];
        filePaths.forEach(async file => {
            const exportReference = require(file);
            tasks.push(Object.values(exportReference)[0]);
        });

        await serial(tasks, async task => {
            logger.info(`Seeding ${task.name}`);
            await task.run();
            logger.info(`${task.name} seeded successfully`);
        });
    } catch (error) {
        logger.error(error.message);
    }
})();
