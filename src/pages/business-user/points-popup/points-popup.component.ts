import { Component } from '@angular/core';
import { ViewController } from 'ionic-angular';
import { GlobalVariable } from './../../../app/app.component';
import { Sql } from './../../../providers/sql';

import { CommonService } from '../../../providers/common';
import { NavParams } from 'ionic-angular/navigation/nav-params';
import { PointsPopUpService } from './points-popup.service';
import { CustomerDetailsService } from '../customer-details/customer-details.service';
import { EditProfileService } from './../edit-profile/edit-profile.service';

@Component({
  selector: "app-points-popup",
  templateUrl: "points-popup.component.html"
})

export class PointsPopUpPage {
  private customerDetails: any;
  private AcceptDewPointRedeemDetails: any;
  private baseUrl = GlobalVariable.BASE_URL;
  private details: any;
  loadTemplate: any = false;

  constructor(
    private commonService: CommonService,
    private navParams: NavParams,
    private viewCtrl: ViewController,
    private profileService: EditProfileService,
    private sql: Sql,
    private customerDetailsService: CustomerDetailsService,
    private pointsPopUpService: PointsPopUpService) {
    this.customerDetails = this.navParams.get('dewRewardDetails');

  }

  ionViewWillEnter() {
    this.getDewPoints();
  }

  getDewPoints(reload = false) {
    this.pointsPopUpService.getDewPointRequest().subscribe((res) => {
      if (res.error == false && reload && res.data.dewPointRequests.length == 0) {
        this.viewCtrl.dismiss();
      }
      if (res.error == false) {
        this.details = res.data;
        this.loadTemplate = true;
      } else {
        this.commonService.toast(res.message);
      }
    });
  }

  acceptDewPointRedeem(rewardId, customer_id, request_id) {
    this.AcceptDewPointRedeemDetails = {
      customerId: customer_id,
      rewardId: rewardId,
      requestId: request_id
    }
    this.commonService.presentLoading();
    this.pointsPopUpService.acceptDewPointRedeem(this.AcceptDewPointRedeemDetails)
      .subscribe((res) => {
        if (res.error == false) {
          this.commonService.dismissLoading();
          this.getDewPoints(true);
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
          this.commonService.toast(res.message);
        } else {
          this.commonService.toast(res.message);
        }
      });

  }

  cancelDewPointRedeem(rewardId, customer_id, request_id) {
    this.AcceptDewPointRedeemDetails = {
      customerId: customer_id,
      rewardId: rewardId,
      requestId: request_id
    }
    this.pointsPopUpService.cancelDewPointRedeem(this.AcceptDewPointRedeemDetails)
      .subscribe((res) => {
        if (res.error == false) {
          this.ionViewWillEnter();          
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
          this.commonService.toast(res.message);
        } else {
          this.commonService.toast(res.message);
        }
      });

  }

}