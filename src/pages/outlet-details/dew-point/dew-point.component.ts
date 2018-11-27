import { RegisterService } from './../../register/register.service';
import { Component } from '@angular/core';
import { Sql } from '../../../providers/sql';
import { NavParams, App, AlertController } from 'ionic-angular';
import { CommonService } from '../../../providers/common';
import { LoginComponent } from '../../login/login.component';
import { DewPointService } from './dew-point.service';
import { GlobalVariable } from '../../../app/app.component';

@Component({
    selector: 'app-dew-point',
    templateUrl: './dew-point.component.html',
})
export class DewPointComponent {
    dewPointList: Array<Object> = [];
    baseUrl: any;
    outletDetails: any;
    customerId: any;
    constructor(private commonService: CommonService,
        private dewPointService: DewPointService,
        private sql: Sql,
        private navParams: NavParams,
        private alertCtrl: AlertController,
        private registerService: RegisterService,
        private app: App) {
        this.baseUrl = GlobalVariable.BASE_URL;
        this.outletDetails = this.navParams.get('outlet');
        this.sql.get('customer_id').then((data) => {
            this.customerId = data;
        });
    }

    ionViewWillEnter() {
        this.commonService.presentLoading();
        this.dewPointService.getDewPoint(this.outletDetails.business_id).subscribe((response: any) => {
            if (response.error == false) {
                this.dewPointList = response.data;
                this.commonService.dismissLoading();
            } else {
                this.commonService.toast(response.message);
                this.commonService.dismissLoading();
            }
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

    confirmRedeem(rewardId: string) {
        let alert = this.alertCtrl.create({
            title: 'Confirm Redeem',
            message: 'Are you sure you want to redeem the Dew Points?',
            buttons: [
                {
                    text: 'No',
                    role: 'no',
                    handler: () => {
                    }
                },
                {
                    text: 'Yes',
                    handler: () => {
                        this.commonService.presentLoading();
                        let redeemDewPointsDetails = {
                            branchId: this.outletDetails._id,
                            rewardId: rewardId
                        }
                        this.dewPointService.redeemDewPoints(redeemDewPointsDetails).subscribe((response: any) => {
                            if (response.error == false) {
                                this.commonService.dismissLoading();
                                this.registerService.getProfileDetails().subscribe((res: any) => {
                                    if (res.error == false) {
                                        let dew_points = {
                                            points: res.data.dew_points,
                                            flag: false
                                        }
                                      this.sql.set('dew_points', res.data.dew_points);
                                      this.registerService.updateCustomerHeaderObservable.next(dew_points);
                                    }
                                  });
                                this.commonService.toast(response.message);
                            } else {
                                this.commonService.toast(response.message);
                                this.commonService.dismissLoading();
                            }
                        }, (error) => {
                            this.commonService.toast('Something went wrong');
                            this.commonService.dismissLoading();
                        });
                    }
                }
            ]
        });
        alert.present();
    }

}
