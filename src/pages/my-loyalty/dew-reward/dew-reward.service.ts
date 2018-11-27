import { CommonService } from './../../../providers/common';
import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../../app/app.component';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class MyLoyaltyDewRewardService {
    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(private commonService:CommonService,
        private http: HttpClient) { }

    public getDewReward() {
        return this.http.get(this.url + "customer/loyalty/dew-rewards")
            .map((res) => res);
    }

    searchBusiness(text) {
        let data = { 
            "search_value" : text
        }
        let details = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "customer/dew-rewards/business-search", details).map( (res) => res);
    }

}