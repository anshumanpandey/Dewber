import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import * as moment from 'moment';
@Component({
    selector: 'app-business-details',
    templateUrl: './promotion-details.component.html',
})
export class PromotionDetailsComponent {
    public header_data: string = null;
    public spendAmount: any = null;
    public startDate: any = null;
    public endDate: any = null;
    public discount_slug: string = null;
    public discount: string = null;
    public loadTemplate: boolean = false;

    private promotionData: any;
    private discount_type: string = null;

    constructor(
        private navParams: NavParams
    ) {
        this.header_data = "Promotions"
        this.promotionData = this.navParams.get('promotions');
        this.discount_type = this.promotionData.discount_type;
        
        if (this.discount_type === "percentage") {
            this.spendAmount = this.promotionData.currency + " " + this.promotionData.spend_amount;
            this.discount = this.promotionData.discount + "%";
            this.discount_slug = 'discount';
        } else {
            this.spendAmount = this.promotionData.currency + " " + this.promotionData.spend_amount;
            this.discount = this.promotionData.currency + " " + this.promotionData.discount;
            this.discount_slug = 'cashback';
        }

        this.startDate = moment(this.promotionData.valid_from).format('YYYY/MM/DD');
        this.endDate = moment(this.promotionData.valid_to).format('YYYY/MM/DD');
        this.loadTemplate = true;
    }

}