import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class ReSetPinService {
    private baseUrl = GlobalVariable.BASE_API_URL;
    private data: any;
    constructor(
        private http: HttpClient,
        private commonService: CommonService
    ) {

    }

    setPin(data: any) {
        this.data = this.commonService.convertURLEncData(data);
        return this.http.post(this.baseUrl + "user/reset-pin", this.data)
            .map((res) => { 
                return res 
            })
            .catch((err) => {
                return Observable.throw(err) });
    }

}