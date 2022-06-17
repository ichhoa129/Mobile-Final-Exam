import { LoginInterceptor } from 'core/modules/auth';
import { GoogleLoginInterceptor } from 'core/modules/auth/interceptor/google-login.interceptor';
import { Module } from 'packages/handler/Module';
import { AuthController } from './auth.controller';

export const AuthResolver = Module.builder()
    .addPrefix({
        prefixPath: '/auth',
        tag: 'auth',
        module: 'AuthModule'
    })
    .register([
        {
            route: '/',
            method: 'post',
            interceptors: [LoginInterceptor],
            body: 'LoginDto',
            controller: AuthController.login,
        },
        {
            route: '/login-with-google',
            method: 'post',
            interceptors: [GoogleLoginInterceptor],
            body: 'GoogleLoginDto',
            controller: AuthController.loginWithGoogle,
        },
    ]);
