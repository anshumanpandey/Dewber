import { Component } from '@angular/core';
import { GlobalVariable } from './../../../app/app.component';
import * as moment from 'moment';
import { NavParams, App, NavController, AlertController } from 'ionic-angular';
import { CustomerDetailsService } from './customer-details.service';
import { CommonService } from '../../../providers/common';
import { Sql } from '../../../providers/sql';
import { LoginComponent } from '../../login/login.component';
import { EditProfileService } from '../edit-profile/edit-profile.service';
import { FormControl, Validators } from '@angular/forms';

@Component({
    selector: 'app-customer-details',
    templateUrl: './customer-details.component.html',
})
export class CustomerDetailsComponent {
    starsCount: number = 0;
    spent_amount = new FormControl('', [Validators.required, Validators.pattern('^[0-9]*.?[0-9][0-9]?')]);
    customerDetail: any;
    customerInfoObject: any;
    from: boolean;
    currency: string;
    baseUrl = GlobalVariable.BASE_URL;

    private spentAmountDetails = {};
    private redeemDewRewardForCustomerDetails = {};
    private dewRewardInfo: any;
    private dewRewardDetails: any;
    private redeemDewRewardForCustomerOption: boolean = false;

    constructor(private navParams: NavParams,
        private customerDetailsService: CustomerDetailsService,
        private commonService: CommonService,
        private sql: Sql,
        private app: App,
        private alertCtrl: AlertController,
        private profileService: EditProfileService,
        private navCtrl: NavController
    ) {
        this.customerDetail = this.navParams.get("customerDetail");
        this.from = this.navParams.get("fromAdd");
        this.sql.get('currency').then((data) => {
            this.currency = data;
        });

        if (this.from) {
            this.customerInfoObject = this.customerDetail;
        } else {
            this.customerInfoObject = this.customerDetail.customerInfo;
        }
    }

    ionViewWillEnter () {
        this.customerDetailsService.getDewRewards(this.customerInfoObject._id).subscribe((res) => {
            this.dewRewardDetails = res.data;
            this.dewRewardInfo = this.dewRewardDetails.dewRewardsCampaings;
            this.dewRewardInfo.forEach((element) => {
                if (element.stampCount == element.target) {
                    element.redeemOptions = true;
                }
            });
        });
    }

    confirmAmount () {
        if (!this.spent_amount.invalid) {
            if (this.spent_amount.value > 60) {
                let alert = this.alertCtrl.create({
                    title: 'Confirm Dew Points ',
                    message: 'Do you want to award these Dew Points?',
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
                                this.sendAmount();
                            }
                        }
                    ]
                });
                alert.present();
            } else {
                this.sendAmount();
            }
        } else {
            this.commonService.toast("Please enter a valid amount");
        }
    }

    sendAmount () {
        if (!this.spent_amount.invalid) {
            this.spentAmountDetails = {
                customerId: this.customerInfoObject._id,
                spent_amount: this.spent_amount.value
            }
            this.commonService.presentLoading();
            this.customerDetailsService.sendSpentAmount(this.spentAmountDetails)
                .subscribe((response) => {
                    if (response.error == false) {
                        this.commonService.dismissLoading();
                        this.commonService.toast(response.message);
                        this.profileService.getProfileDetails().subscribe((res: any) => {
                            if (res.error == false) {
                                let dew_points = {
                                    points: res.data.dew_points,
                                    flag: false
                                }
                                this.sql.set('dew_points', res.data.dew_points);
                                this.customerDetailsService.updateBusinessHeaderObservable.next(dew_points);
                            }
                        })
                        this.navCtrl.pop();
                    }
                    else {
                        this.commonService.dismissLoading();
                        this.commonService.toast(response.message);
                    }
                });
        } else {
            this.commonService.toast("Please enter a valid amount");
        }
    }

    convertDate (date) {
        return moment(date).fromNow();
    }

    redeemDewRewardForCustomer (rewardId: string) {
        this.redeemDewRewardForCustomerDetails = {
            customerId: this.customerInfoObject._id,
            rewardId: rewardId
        };
        this.commonService.presentLoading();
        this.customerDetailsService.redeemDewRewardForCustomer(this.redeemDewRewardForCustomerDetails)
            .subscribe((response) => {
                if (response.error == false) {
                    this.commonService.dismissLoading();
                    //subject
                    this.redeemDewRewardForCustomerOption = !this.redeemDewRewardForCustomerOption
                    this.profileService.getProfileDetails().subscribe((res: any) => {
                        if (res.error == false) {
                            let dew_points = {
                                points: res.data.dew_points,
                                flag: false
                            }
                            this.sql.set('dew_points', res.data.dew_points);
                            this.customerDetailsService.updateBusinessHeaderObservable.next(dew_points);
                        }
                    })

                    this.commonService.toast(response.message);
                    this.ionViewWillEnter();
                }
                else {
                    this.commonService.dismissLoading();
                    this.commonService.toast(response.message);
                }
            });
    }

    stampDewRewards (rewardId: string) {
        this.commonService.presentLoading();
        let stampDewRewards = {
            customerId: this.customerInfoObject._id,
            rewardId: rewardId
        };
        this.customerDetailsService.stampDewRewards(stampDewRewards).subscribe((response) => {
            if (response.error == false) {
                this.commonService.dismissLoading();
                this.commonService.toast(response.message);
                this.ionViewWillEnter();
            }
            else {
                this.commonService.dismissLoading();
                this.commonService.toast(response.message);
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

    removeDewRewardStamp (rewardId: string) {
        this.commonService.presentLoading();
        let stampDewRewards = {
            customerId: this.customerInfoObject._id,
            rewardId: rewardId
        };

        this.customerDetailsService.removeDewRewardStamp(stampDewRewards).subscribe((response) => {
            if (response.error == false) {
                this.commonService.dismissLoading();
                this.commonService.toast(response.message);
                this.ionViewWillEnter();
            }
            else {
                this.commonService.dismissLoading();
                this.commonService.toast(response.message);
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
}   