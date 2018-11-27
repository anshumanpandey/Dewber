import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { AuthService } from '../pages/login/auth.service';

@Injectable()
export class MyHttpInterceptor implements HttpInterceptor {
    token: string = null;

    constructor(
        private authService: AuthService
    ) {
    }

    intercept (req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if ((req.url.indexOf('country-list') > -1)) {
            return next.handle(req);
        }
        if (!(req.url.indexOf('signup') > -1)) {
            req = req.clone({ headers: req.headers.append('Content-Type', 'application/x-www-form-urlencoded') });
        }

        if (req.url.indexOf("/login") > -1 || req.url.indexOf("/get-otp") > -1 || req.url.indexOf("/otp") > -1 || req.url.indexOf("/signup") > -1 || req.url.indexOf("/reset-pin") > -1 || req.url.indexOf("/set-pin") > -1) {
            return next.handle(req);
        } else {
            let token = this.authService.getToken();

            req = req.clone({ headers: req.headers.append("X-Authorization", token) });

            return next.handle(req);

        }

    }

}