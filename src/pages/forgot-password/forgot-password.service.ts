import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../app/app.component';
import { HttpClient } from '@angular/common/http';
import { CommonService } from '../../providers/common';

@Injectable()
export class ForgotPasswordService {
    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(private http: HttpClient,
        private commonService: CommonService) { }

    forgotPassword(mobile) {
        this.data = this.commonService.convertURLEncData(mobile);
        return this.http.post(this.url + "customer/get-otp", this.data).map((res) => res);
    }
}
