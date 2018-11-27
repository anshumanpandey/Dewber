import { Injectable } from '@angular/core';
import { CommonService } from '../../../providers/common';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../../app/app.component';

@Injectable()
export class MyLoyaltyDewPointService {

    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(private commonService: CommonService,
        private http: HttpClient) { }

    public getDewPoint() {
        return this.http.get(this.url + "customer/loyalty/dew-points")
            .map((res) => res);
    }

    searchBusiness(text) {
        let data = { 
            "search_value" : text
        }
        let details = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "customer/dew-points/business-search", details).map( (res) => res);
    }
}
