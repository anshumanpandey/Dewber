import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVariable } from '../../../app/app.component';
import { Observable } from 'rxjs/Observable';
import { CommonService } from '../../../providers/common';

@Injectable()
export class PointsPopUpService {
    private url = GlobalVariable.BASE_API_URL;
    private data: any;
    constructor(private http: HttpClient,
        private commonService: CommonService
    ) {

    }

    acceptDewPointRedeem(data) {
        this.data = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "business-user/redeem-dew-point", this.data)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    cancelDewPointRedeem(data) {
        this.data = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "business-user/cancel-redeem-dew-point", this.data)
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }

    getDewPointRequest() {
        return this.http.get(this.url + "business-user/dew-point-request")
            .map((res) => res)
            .catch((err) => Observable.throw(err));
    }


}