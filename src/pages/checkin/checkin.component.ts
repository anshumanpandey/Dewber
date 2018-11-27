import { Component } from '@angular/core';
import { NavParams, NavController } from 'ionic-angular';
import { GlobalVariable } from '../../app/app.component';
import { OutletDetailsComponent } from '../outlet-details/outlet-details.component';
import { CheckinService } from './checkin.service';
import { CommonService } from '../../providers/common';

@Component({
    selector: 'app-checkin',
    templateUrl: './checkin.component.html',
})
export class CheckInComponent {
    public outlet: any;
    public isFromCheckInButton: boolean;
    public header_data: string = null;
    public baseUrl: any;
    
    private checkInDetails = {};
    constructor(
        private navParams: NavParams,
        private navCtrl: NavController,
        private checkInService: CheckinService,
        private commonService: CommonService
    ) {
        this.baseUrl = GlobalVariable.BASE_URL;
        this.outlet = this.navParams.get('outlet');
        this.checkInDetails = {
            'businessId': this.outlet.business_id,
            'branchId': this.outlet._id
        }
        this.isFromCheckInButton = this.navParams.get('isFromCheckInButton');
        this.header_data = this.outlet.branch_name;

        if (this.isFromCheckInButton == true) {
            setTimeout(() => {
                this.navCtrl.push(OutletDetailsComponent, { "outlet": this.outlet });
            }, 1000);
        }
    }

    outletDetails() {
        this.navCtrl.push(OutletDetailsComponent, { "outlet": this.outlet });
    }

    checkIn() {
        this.commonService.presentLoading();
        this.checkInService.postCheckInDetails(this.checkInDetails).subscribe((res:any) => {
            if (res.error == true) {
                this.commonService.presentLoading();
                this.navCtrl.push(OutletDetailsComponent, { "outlet": this.outlet });
            } else {
                this.commonService.presentLoading();
                this.commonService.toast("Something went wrong");
            }
        });
    }
}