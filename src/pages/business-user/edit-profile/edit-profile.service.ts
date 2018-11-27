import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import 'rxjs/Rx';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';


@Injectable()
export class EditProfileService {

    private url = GlobalVariable.BASE_API_URL;;  // URL to web api
    data: any;

    constructor(private http: HttpClient,
        private commonService: CommonService,
    ) { }

    editProfile(data) {
        this.data = this.commonService.convertURLEncData(data);
        return this.http.post(this.url + "business-user/update-profile", this.data)
            .map((res) => res);
    }

    getProfileDetails() {
        return this.http.get(this.url + "business-user/profile")
            .map((res) => res)
    };
}