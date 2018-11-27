import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';

@Injectable()
export class BusinessUserProfileService {
    private url = GlobalVariable.BASE_API_URL;
    private data: any;
    constructor(
        private http: HttpClient,
        private commonService: CommonService
    ) {

    }
    public postFeedback(feedBackData) {
        this.data = this.commonService.convertURLEncData(feedBackData);
        return this.http.post(this.url + "customer/feedback", this.data)
            .map((res) => res).catch((err) => {
                return Observable.throw(err);
            });
    }
    public logout() {
        return this.http.get(this.url + "business-user/logout")
            .map((res) => {
                res
            }).catch((err) => {
                return Observable.throw(err);
            });
    }

    public switchUser(switchAccountId) {
        this.data = this.commonService.convertURLEncData(switchAccountId);
        return this.http.post(this.url + "business-user/switch-account", this.data)
            .map((res) => res);
    }

    public changeDefaultLogin(item) {
        this.data = this.commonService.convertURLEncData(item);
        return this.http.post(this.url + "business-user/update-default-login", this.data)
            .map((res) => res);
    }

}