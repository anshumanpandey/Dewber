import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import 'rxjs/add/operator/share';
import 'rxjs/add/operator/map';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';

@Injectable()
export class RegularsService {
    private url = GlobalVariable.BASE_API_URL;

    data: any;

    constructor(
        private http: HttpClient,
        private commonService: CommonService) {
    }

    public postDetails(geoDetails) {

        this.data = this.commonService.convertURLEncData(geoDetails);

        return this.http.post(this.url + "customer/regulars", this.data)
            .map((res) => res);
    }



}