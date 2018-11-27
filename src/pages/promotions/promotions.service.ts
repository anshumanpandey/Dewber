import { Injectable } from '@angular/core';
import { GlobalVariable } from '../../app/app.component';
import { HttpClient } from '@angular/common/http';
import { Events } from 'ionic-angular';
import { Sql } from '../../providers/sql';
import { CommonService } from '../../providers/common';

@Injectable()
export class PromotionsService {

    private url = GlobalVariable.BASE_API_URL;
    data: any;
    private promotions_count: number;
    constructor(
        private http: HttpClient,
        private events: Events,
        private commonService: CommonService,
        private sql: Sql
    ) {
        this.sql.get('count').then((res) => {
            this.promotions_count = res;
        })
    }

    public getPromotion(geoDetails) {
        this.data = this.commonService.convertURLEncData(geoDetails);
        return this.http.post(this.url + "customer/business-promotions", this.data)
            .map((res) => res);
    }

    readPromotion(id) {
        this.data = this.commonService.convertURLEncData(id);
        return this.http.post(this.url + "customer/promotion/read", this.data)
            .map((res) => res);
    }

    updatePromotions() {
        this.events.publish('promotions:badge', this.promotions_count);
    }
}