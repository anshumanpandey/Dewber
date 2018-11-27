import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../providers/common';
import { GlobalVariable } from '../../app/app.component';


@Injectable()
export class ProfileService {
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

    public switchUser(switchAccountId) {
        this.data = this.commonService.convertURLEncData(switchAccountId);
        return this.http.post(this.url + "customer/switch-account", this.data)
            .map((res) => res);
    }

    getProfileDetails() {
        return this.http.get(this.url + "customer/profile")
            .map((res) => res)
    };

    public changeDefaultLogin(item) {
        this.data = this.commonService.convertURLEncData(item);
        return this.http.post(this.url + "customer/update-default-login", this.data)
            .map((res) => res);
    }
}
