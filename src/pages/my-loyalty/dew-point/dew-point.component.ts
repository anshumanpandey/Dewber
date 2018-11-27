import { FormControl, FormGroup } from '@angular/forms';
import { Component, OnDestroy } from '@angular/core';
import { ModalController, App, Events } from 'ionic-angular';
import { DewPointsDetailsComponent } from './dew-point-details/dew-point-details.component';
import { DewPointService } from '../../outlet-details/dew-point/dew-point.service';
import { CommonService } from '../../../providers/common';
import { MyLoyaltyDewPointService } from './dew-point.service';
import { LoginComponent } from '../../login/login.component';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-dew-point',
    templateUrl: './dew-point.component.html',
})
export class MyLoyaltyDewPointComponent implements OnDestroy {
    private dewPoints: any;
    private dewPointsDetail: any;
    private dew_point: any;
    private dew_points: number;

    masterOutletDetails: Array<any>;
    myOutletDetails: Array<any>;
    myOutletBusinessDetails: Array<any>;
    searchControl: FormGroup;
    searchSubscription: Subscription;
    businessSearch: boolean = false;

    constructor(
        private modalCtrl: ModalController,
        private dewPointService: MyLoyaltyDewPointService,
        private events: Events,
        private commonService: CommonService,
        private app: App
    ) {
        this.searchControl = new FormGroup({
            'search': new FormControl(null)
        });
    }

    ionViewWillEnter() {
        console.log("will enter");
        this.searchControl.controls['search'].setValue(null);

        this.commonService.presentLoading();
        this.dewPointService.getDewPoint().subscribe((response: any) => {
            if (response.error == false) {
                this.commonService.dismissLoading();
                this.myOutletDetails = response.data;
                this.masterOutletDetails = response.data;

            } else {
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

    getTotalDewPoints(data) {
        let calc_dew_points = 0;
        data.forEach(element1 => {
            calc_dew_points = (calc_dew_points) + (element1.dew_points);
        });
        return calc_dew_points;
    }

    getDewPointDetails(dewPoint) {
        let dewPointDetailModal = this.modalCtrl.create(DewPointsDetailsComponent, { 'data': { 'dewPoint':  dewPoint, 'branch': false}});
        dewPointDetailModal.present();
    }

    getBranchDetails(dewPoint) {
        let dewPointDetailModal = this.modalCtrl.create(DewPointsDetailsComponent, { 'data': { 'dewPoint': dewPoint, 'branch': true } });
        dewPointDetailModal.present();

        dewPointDetailModal.onDidDismiss( () => {
            this.searchControl.controls['search'].setValue(null);
        })
    }

    ionViewDidEnter() {
        this.searchSubscription = this.searchControl.valueChanges.debounceTime(700).subscribe(search => {
            if (search.search && search.search != "") {
                this.searchBusiness(search.search);
            } else {
                this.businessSearch = false;
                this.ionViewWillEnter();
            }
        });
    }

    searchBusiness(text) {
        if (text && text != "") {
            this.businessSearch = true;
            this.dewPointService.searchBusiness(text).subscribe((response: any) => {
                if (response.error == false) {
                    this.myOutletBusinessDetails = response.data;

                } else {
                    this.commonService.toast(response.message);
                }
            }, (error) => {
                this.commonService.logout().then(() => {
                    this.app.getRootNav().setRoot(LoginComponent);
                }).catch(() => {
                    this.commonService.toast('Something went wrong');
                });
            });
        } else {
            this.businessSearch = false;
            this.ionViewWillEnter();
        }
    }

    ionViewWillLeave() {
        this.searchSubscription.unsubscribe();
    }

    ngOnDestroy() {
        this.events.unsubscribe("leaving");
    }
}
