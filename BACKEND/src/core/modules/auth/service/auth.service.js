import { DataPersistenceService } from 'packages/restBuilder/core/dataHandler';
import { pick } from 'lodash';
import { JwtPayload } from 'core/modules/auth/dto/jwt-sign.dto';
import { OAuthService } from 'core/modules/auth/service/oauth.service';
import { Optional } from 'core/utils';
import { CreateUserWithGoogleDto } from 'core/modules/user/dto/createUserWithGoogle.dto';
import { BcryptService } from './bcrypt.service';
import { JwtService } from './jwt.service';
import { UserRepository } from '../../user/user.repository';
import { UnAuthorizedException } from '../../../../packages/httpException';

class Service extends DataPersistenceService {
    constructor() {
        super(UserRepository);
        this.jwtService = JwtService;
        this.bcryptService = BcryptService;
        this.oAuthService = OAuthService;
        this.userRepository = UserRepository;
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

    async loginWithGoogle(loginWithGoogleDto) {
        const userInfo = Optional.of(await this.oAuthService.verify(loginWithGoogleDto.token))
            .throwIfNotPresent(new UnAuthorizedException('Invalid token'))
            .get();

        let foundUser = await this.userRepository.findByEmail(userInfo.email);
        let user = foundUser;
        if (!foundUser) {
            foundUser = await this.userService.upsertOne(CreateUserWithGoogleDto(userInfo));
            user = await this.userRepository.findByEmail(userInfo.email);
        }
        return this.login(user);
    }

    #getUserInfo = user => pick(user, ['_id', 'email', 'username', 'roles']);
}

export const AuthService = new Service();
