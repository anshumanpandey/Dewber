import { Injectable } from '@angular/core';
import 'rxjs/Rx';

@Injectable()
export class AuthService {
    public authToken: string;
    public isLoggedIn: boolean;

    constructor() { }

    getToken(): string {
        return this.authToken;
    }

    getLoginStatus() : boolean {
        return this.isLoggedIn;
    }
}