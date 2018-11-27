import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/map';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';

@Injectable()
export class NearByService {
    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(
        private http: HttpClient,
        private commonService: CommonService,
    ) {
    }

    public postDetails(geoDetails) {
        this.data = this.commonService.convertURLEncData(geoDetails);
        return this.http.post(this.url + "customer/near-by", this.data)
            .map((res) => res);
    }
    
    public postCheckInDetails(checkInDetails) {
        this.data = this.commonService.convertURLEncData(checkInDetails);
        return this.http.post(this.url + "customer/checkin", this.data)
            .map((res) => res);
    }
  
    searchBusiness(text, long, lat) {
        let data = { 
            "search_value" : text,
            "longitude": long,
            "latitude": lat
        }
        let details = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "customer/near-by/search", details).map( (res) => res);
    }
}