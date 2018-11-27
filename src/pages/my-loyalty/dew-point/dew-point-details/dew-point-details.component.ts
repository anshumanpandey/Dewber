import { Sql } from './../../../../providers/sql';
import { RegisterService } from './../../../register/register.service';
import { Component } from '@angular/core';
import { NavParams, AlertController, ViewController } from 'ionic-angular';
import { GlobalVariable } from '../../../../app/app.component';
import { CommonService } from '../../../../providers/common';
import { DewPointDetailsService } from './dew-point-details.service';

@Component({
    selector: 'app-dew-point-details',
    templateUrl: './dew-point-details.component.html',
})
export class DewPointsDetailsComponent {
    private header_data: string = "My Loyalty";
    private dewPoints: any;
    private baseUrl = GlobalVariable.BASE_URL;
    private branchId: any;
    private branchIdList: any;
    private isBranchId: boolean;

    constructor(
        private commonService: CommonService,
        private navParam: NavParams,
        private registerService: RegisterService,
        private sql: Sql,
        private alertCtrl: AlertController,
        private dewPointDetailService: DewPointDetailsService,
        private viewCtrl: ViewController
    ) {
        this.dewPoints = this.navParam.get('data').dewPoint;
        this.isBranchId = this.navParam.get('data').branch;
    }


    confirmRedeem (dewPointDetail) {
        let alert = this.alertCtrl.create({
            title: 'Confirm Redeem',
            message: 'Are you sure want to redeem Dew Points?',
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
                        const branchId = this.isBranchId ? this.dewPoints._id : this.dewPoints.branchId;

                        const redeemDewPointsDetails = {
                            branchId: branchId,
                            rewardId: dewPointDetail._id
                        }
                        this.dewPointDetailService.redeemDewPoints(redeemDewPointsDetails).subscribe((response: any) => {
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
                                this.viewCtrl.dismiss();
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