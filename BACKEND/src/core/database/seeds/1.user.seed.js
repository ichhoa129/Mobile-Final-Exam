/* eslint-disable import/no-extraneous-dependencies */
// import { UserModel } from 'core/modules/user/user.model';
// import faker from 'faker';

export class UserSeeder {
    static async run() {
        // const userStatuses = [UserStatus.ONLINE, UserStatus.OFFLINE];
        // const sampleData = [
        //     {
        //         email: 'ichhoa129@gmail.com',
        //         username: 'Ich Hoa Nguyen',
        //         status: UserStatus.ONLINE,
        //         avatar: {
        //             url: `${faker.image.imageUrl()}?random=${Math.round(Math.random() * 1000)}`,
        //         },
        //         createdAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         updatedAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         deletedAt: null,
        //     },
        //     {
        //         email: 'huynhphuquy712001asus4.4.2@gmail.com',
        //         username: 'Huỳnh Phú Quý',
        //         status: UserStatus.ONLINE,
        //         avatar: {
        //             url: `${faker.image.imageUrl()}?random=${Math.round(Math.random() * 1000)}`,
        //         },
        //         createdAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         updatedAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         deletedAt: null,
        //     },
        //     {
        //         email: 'hqhung201@gmail.com',
        //         username: 'Hoàng Quang Hùng',
        //         status: UserStatus.ONLINE,
        //         avatar: {
        //             url: `${faker.image.imageUrl()}?random=${Math.round(Math.random() * 1000)}`,
        //         },
        //         createdAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         updatedAt: faker.date.between('2015-01-01', '2017-12-01'),
        //         deletedAt: null,
        //     },
        // ];

        // for (let i = 0; i < 30; i += 1) {
        //     sampleData.push({
        //         email: faker.internet.email(),
        //         username: faker.name.findName(),
        //         status: userStatuses[Math.floor(Math.random() * userStatuses.length)],
        //         avatar: {
        //             url: `${faker.image.imageUrl()}?random=${Math.round(Math.random() * 1000)}`,
        //         },
        //         createdAt: faker.date.between('2015-01-01', '2021-12-01'),
        //         updatedAt: faker.date.between('2015-01-01', '2021-12-01'),
        //         deletedAt: null,
        //     });
        // }
        // await UserModel.deleteMany({});
        // return UserModel.insertMany(sampleData);
    }
}
