import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from './../../../providers/common';

@Injectable()
export class DewPointService {

    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(private commonService: CommonService,
        private http: HttpClient) { }

    getDewPoint(businessId: string) {
        return this.http.get(this.url + "customer/dew-points/" + businessId)
            .map((res) => res);
    }

    redeemDewPoints(details: any) {
        let data = this.commonService.convertURLEncData(details);
        return this.http.post(this.url + "customer/request-dew-point-redeem", data)
            .map((res) => res);
    }
}