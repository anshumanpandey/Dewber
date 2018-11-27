import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../app/app.component';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../providers/common';

@Injectable()
export class ChangePinService {
    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(private http: HttpClient,
        private commonService: CommonService) { }

    changePassword(data) {
        this.data = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "customer/change-pin", this.data)
            .map((res) => res).catch((err) => {
                return Observable.throw(err);
            });
    }
}