import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class UserRegistrationService {

    private url = GlobalVariable.BASE_API_URL;  // URL to web api

    constructor(
        private http: HttpClient,
        private commonService: CommonService
    ) {

    }

    saveUser(data) {
       let details = this.commonService.convertURLEncData(data);
        
        return this.http.post(this.url + "business-user/customer/add", details)
            .map((res) => res);
    }

    getDewRewards(businessId) {
        return this.http.get(this.url + "business-user/customer/" + businessId)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

}