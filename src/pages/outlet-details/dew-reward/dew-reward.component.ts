import { Component } from '@angular/core';
import { CommonService } from '../../../providers/common';
import { DewRewardService } from './dew-reward.service';
import { LoginComponent } from '../../login/login.component';
import { NavParams, App, NavController, Events } from 'ionic-angular';
import { GlobalVariable } from '../../../app/app.component';
import { PromotionDetailsComponent } from '../../promotion-details/promotion-details.component';

@Component({
    selector: 'app-dew-reward',
    templateUrl: './dew-reward.component.html',
})
export class DewRewardComponent {
    dewRewardDetails: Array<Object> = [];
    promotionDetails: Array<Object> = [];
    baseUrl: any;
    outletDetails: any;

    constructor(private commonService: CommonService,
        private dewRewardService: DewRewardService,
        private navParams: NavParams,
        private event: Events,
        private navCtrl: NavController,
        private app: App) {
        this.baseUrl = GlobalVariable.BASE_URL;
        this.outletDetails = this.navParams.get('outlet');
    }

    ionViewWillEnter() {
        console.log("in enter dew reward");
        this.event.publish('headerChange', true);
        this.dewRewardService.getDewReward(this.outletDetails.business_id).subscribe((response: any) => {
            if (response.error == false) {
                this.dewRewardDetails = response.data;
                this.dewRewardDetails.forEach((element: any) => {
                    element.emptyStamp = element.target - element.stampCount;
                });
            } else {
                this.commonService.toast(response.message);
            }

            this.commonService.presentLoading();
            this.dewRewardService.getPromotion(this.outletDetails.business_id).subscribe((response: any) => {
                if (response.error == false) {
                    this.promotionDetails = response.data;
                } else {
                    this.commonService.toast(response.message);
                }
                this.commonService.dismissLoading();
            }, (error) => {
                this.commonService.logout().then(() => {
                    this.commonService.dismissLoading();
                    this.app.getRootNav().setRoot(LoginComponent);
                }).catch(() => {
                    this.commonService.dismissLoading();
                    this.commonService.toast('Something went wrong');
                });
            });
        }, (error) => {
            this.commonService.logout().then(() => {
                this.commonService.dismissLoading();
                this.app.getRootNav().setRoot(LoginComponent);
            }).catch(() => {
                this.commonService.dismissLoading();
                this.commonService.toast('Something went wrong');
            });
        });

    }

    getTimes(n) {
        let array = [];
        for (let i = 0; i < n; i++) {
            array.push({})
        }
        return array;
    }

    promotions(promotions) {
        this.event.publish('headerChange', false);
        this.navCtrl.push(PromotionDetailsComponent, { 'promotions': promotions })
    }
}