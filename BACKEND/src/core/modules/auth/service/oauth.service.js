import { adminApp } from '../../../config/firebase.config';

class OAuthServiceImpl {
    verify = async token => adminApp.auth().verifyIdToken(token);
}

export const OAuthService = new OAuthServiceImpl();
