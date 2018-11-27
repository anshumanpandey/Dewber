import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';

import { GlobalVariable } from '../../app/app.component';
import { CommonService } from '../../providers/common';

@Injectable()

export class NotificationSettingService {

    private url = GlobalVariable.BASE_API_URL;;  // URL to web api
    data: any;
   

    constructor(private http: HttpClient,
        private commonService: CommonService,
    ) { }

   
    updateSetting(data) {
        this.data = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "customer/update-profile", this.data)
            .map((res) => res);
    }
}