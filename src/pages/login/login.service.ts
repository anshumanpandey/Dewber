import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';

@Injectable()

export class LoginService {
    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(private http: HttpClient,
        private commonService: CommonService) { }

    login(data) {
        this.data = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "user/login", this.data.toString())
            .map((res) => res).catch((err) => {
                return Observable.throw(err);
            });
    }
  
}

// https://dewber-nodejs.herokuapp.com/api/user/login
