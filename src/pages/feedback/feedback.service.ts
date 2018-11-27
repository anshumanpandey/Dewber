import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import 'rxjs/Rx';
import { Observable } from 'rxjs/Observable';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';

@Injectable()
export class SendFeedBackService {
    private url = GlobalVariable.BASE_API_URL;  // URL to web api
    data: any;

    constructor(private http: HttpClient,
        private commonService: CommonService) { }

        public postFeedback(feedBackData) {
            this.data = this.commonService.convertURLEncData(feedBackData);
            return this.http.post(this.url + "customer/feedback", this.data)
                .map((res) => res).catch((err) => {
                    return Observable.throw(err);
                });
        }
}