import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';
import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';
import { HttpClient } from '@angular/common/http';


@Injectable()
export class HeaderService {
    searchObservable = new Subject<Boolean>();
    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(private commonService: CommonService,
        private http: HttpClient) {
    }

    searchFlag(): Observable<any> {
        return this.searchObservable.asObservable();
    }

    redeemDewRewards(data) {    // Request Type Dew Reward
        this.data = this.commonService.convertURLEncData(data);

        return this.http.post(this.url + "customer/scan-dew-reward", this.data)
            .map((res) => res);
    }
}