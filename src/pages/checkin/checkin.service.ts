import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../app/app.component';
import { HttpClient } from '@angular/common/http';
import { CommonService } from '../../providers/common';

@Injectable()
export class CheckinService {

    private url = GlobalVariable.BASE_API_URL;

    data: any;
    constructor(
        private http: HttpClient,
        private commonService: CommonService,
    ) {

    }
    public postCheckInDetails(checkInDetails) {
        this.data = this.commonService.convertURLEncData(checkInDetails);
        return this.http.post(this.url + "customer/checkin", this.data)
            .map((res) => res);
    }
    ionViewDidLeave () {
        console.log("check in lave");
    }
}