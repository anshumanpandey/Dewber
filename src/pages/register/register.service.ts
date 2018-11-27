import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';
import { BehaviorSubject } from 'rxjs/Rx';

@Injectable()

export class RegisterService {

    private url = GlobalVariable.BASE_API_URL;;  // URL to web api
    data: any;
    header: HttpHeaders;
    updateCustomerHeaderObservable = new BehaviorSubject<any>(false);

    constructor(private http: HttpClient,
        private commonService: CommonService,
    ) { }

    getCountryCodes (): Observable<any> {
        return this.http.get(this.url + "country-list")
            .map((res) => res);
    }

    register (data) {
        this.data = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "customer/signup", this.data)
            .map((res) => { return res })
            .catch((err) => Observable.throw(err));
    }

    updateProfile (data) {
        this.data = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "customer/update-profile", this.data)
            .map((res) => res);
    }

    getProfileDetails () {
        return this.http.get(this.url + "customer/profile")
            .map((res) => res)
    };

    updateCustomerHeader (): Observable<any> {
        return this.updateCustomerHeaderObservable.asObservable();
    }

}
