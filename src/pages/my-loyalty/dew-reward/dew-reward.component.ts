import { Component } from '@angular/core';
import { ModalController } from 'ionic-angular';
import { MyLoyaltyDewRewardService } from './dew-reward.service';
import { CommonService } from '../../../providers/common';
import { GlobalVariable } from '../../../app/app.component';
import { DewRewardDetailsComponent } from './dew-reward-details/dew-reward-details.component';
import { FormControl, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-dew-reward',
    templateUrl: './dew-reward.component.html',
})
export class MyLoyaltyDewRewardComponent {
    private dewRewards: any;
    private baseUrl = GlobalVariable.BASE_URL
    masterOutletDetails: Array<any>;
    myOutletDetails: Array<any>;
    searchControl: FormGroup;
    searchSubscription: Subscription;

    constructor(private dewRewardService: MyLoyaltyDewRewardService,
        private modalCtrl: ModalController,
        private commonService: CommonService) {
        this.searchControl = new FormGroup({
            'search': new FormControl(null)
        });
    }

    ionViewWillEnter() {
        this.searchControl.controls['search'].setValue(null);
        this.commonService.presentLoading();
        this.dewRewardService.getDewReward()
            .subscribe((res: any) => {
                if (res.error == false) {
                    this.commonService.dismissLoading();
                    this.myOutletDetails = res.data;
                    this.masterOutletDetails = res.data;
                    this.myOutletDetails.forEach((outlet) => {
                        let stampcount = 0;
                        outlet.dewRewardsCampaings.forEach(campaign => {
                            stampcount += campaign.stampCount;
                        });
                        if (stampcount > 0) {
                            outlet.show_campaigns = true;
                        } else {
                            outlet.show_campaigns = false;
                        }
                        debugger;
                    });
                } else {
                    this.commonService.dismissLoading();
                    this.commonService.toast(res.message);
                }
            })
    }

    ionViewDidEnter() {
        this.searchSubscription = this.searchControl.valueChanges.debounceTime(700).subscribe(search => {
            this.filterCustomers(search);
        });
    }

    dewRewardsDetails() {
        let modalCreate = this.modalCtrl.create(DewRewardDetailsComponent);
        modalCreate.present();
    }

    getDewRewardDetails(dewReward) {
        let modalCreate = this.modalCtrl.create(DewRewardDetailsComponent, { 'dewReward': dewReward });
        modalCreate.present();
    }

    filterCustomers(searchText) {
        if (searchText.search && searchText.search != "") {
            searchText = searchText.search.toUpperCase();
            this.myOutletDetails = this.masterOutletDetails.filter(item => {
                return item.business_name.toUpperCase().indexOf(searchText) > -1
            });
        } else {
            this.myOutletDetails = this.masterOutletDetails;
        }

    }

    ionViewWillLeave() {
        this.searchControl.controls['search'].setValue(null);
        this.searchSubscription.unsubscribe();
    }


}