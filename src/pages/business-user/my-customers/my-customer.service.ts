import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';



@Injectable()
export class MyCustomerService {

    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(
        private http: HttpClient,
        private commonService: CommonService
    ) {

    }

    getCustomerDetails() {
        return this.http.get(this.url + "business-user/customers")
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    search(key) {
        this.data = this.commonService.convertURLEncData(key);
        return this.http.post(this.url + "business-user/search", this.data)
            .map((res) => res)
            .catch((err) => Observable.throw(err));

    }

    getDetails(customerId: string) {
        return this.http.get(this.url + "business-user/customer/" + customerId)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    searchCustomer(text) {
        let data = { 
            "search_value" : text
        }
        let details = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "business-user/search", details).map( (res) => res);
    }
}