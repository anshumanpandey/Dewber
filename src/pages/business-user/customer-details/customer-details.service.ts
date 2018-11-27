import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';

@Injectable()
export class CustomerDetailsService {

    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    updateBusinessHeaderObservable = new BehaviorSubject<any>(false);

    constructor(
        private http: HttpClient,
        private commonService: CommonService
    ) {

    }

    stampDewRewards (data) {
        let details = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "business-user/stamp-dew-reward", details)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    removeDewRewardStamp (data) {
        let details = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "business-user/dew-reward/remove-stamp", details)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    getDewRewards (businessId) {
        return this.http.get(this.url + "business-user/customer/" + businessId)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    sendSpentAmount (amount) {
        let details = this.commonService.convertURLEncData(amount);

        return this.http.post(this.url + "business-user/add-spent-amount", details)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    redeemDewRewardForCustomer (data) {
        let details = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "business-user/redeem-dew-reward", details)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    updateBusinessHeader (): Observable<any> {
        return this.updateBusinessHeaderObservable.asObservable();
    }


}