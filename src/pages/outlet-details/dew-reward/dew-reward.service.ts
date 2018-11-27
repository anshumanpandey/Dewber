import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../../app/app.component';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class DewRewardService {
    private url = GlobalVariable.BASE_API_URL;
    data: any;

    constructor(
        private http: HttpClient) { }

    public getDewReward(businessId: string) {
        return this.http.get(this.url + "customer/dew-rewards/" + businessId)
            .map((res) => res);
    }

    public getPromotion(businessId: string) {
        return this.http.get(this.url + "customer/promotions/" + businessId)
            .map((res) => res);
    }
}