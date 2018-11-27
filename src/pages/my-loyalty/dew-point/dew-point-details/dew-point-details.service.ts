import { Injectable } from '@angular/core';
import { CommonService } from '../../../../providers/common';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../../../app/app.component';

@Injectable()
export class DewPointDetailsService {
    
    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(private commonService: CommonService,
    private http: HttpClient) {

    }

    redeemDewPoints(details: any) {
        let data = this.commonService.convertURLEncData(details);
        return this.http.post(this.url + "customer/request-dew-point-redeem", data)
            .map((res) => res);
    }
}