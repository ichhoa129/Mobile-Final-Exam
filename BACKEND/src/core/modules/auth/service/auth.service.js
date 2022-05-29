import { DataPersistenceService } from 'packages/restBuilder/core/dataHandler';
import { pick } from 'lodash';
import { JwtPayload } from 'core/modules/auth/dto/jwt-sign.dto';
import { BcryptService } from './bcrypt.service';
import { JwtService } from './jwt.service';
import { UserRepository } from '../../user/user.repository';
import { UnAuthorizedException } from '../../../../packages/httpException';

class Service extends DataPersistenceService {
    constructor() {
        super(UserRepository);
        this.jwtService = JwtService;
        this.bcryptService = BcryptService;
    }

    async login(loginDto) {
        const user = await this.repository.findByEmail(loginDto.email);
        if (user && this.bcryptService.compare(loginDto.password, user.password)) {
            return {
                user: this.#getUserInfo(user),
                accessToken: this.jwtService.sign(JwtPayload(user)),
            };
        }
        throw new UnAuthorizedException('Email or password is incorrect');
    }

    #getUserInfo = user => pick(user, ['_id', 'email', 'username', 'roles']);
}

export const AuthService = new Service();
