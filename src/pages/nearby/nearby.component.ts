import { HeaderService } from './../header/header.service';
import { Component, OnDestroy } from '@angular/core';
import { NavController, App, ModalController, PopoverController, Events } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';
import { CommonService } from './../../providers/common';

import { GlobalVariable } from '../../app/app.component';
import { CheckInComponent } from '../checkin/checkin.component';
import { LoginComponent } from '../login/login.component';

import { NearByService } from './nearby.service';
import { FormControl, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { BehaviorSubject } from 'rxjs/Rx';


@Component({
    selector: 'app-nearby',
    templateUrl: './nearby.component.html',
})
export class NearByComponent implements OnDestroy {
    private longitude: number;
    private latitude: number;
    private geoDetails = {};
    private outlets: Array<any> = [];
    private checkInDetails = {};

    public baseUrl: any;
    public header_data = "Nearby";
    public searchComplete: any = '';
    public masterOutletDetails: Array<any>;
    public headerSubscription: Subscription;
    public searchSubscription: Subscription;
    public searchOption: boolean = false;
    public searchControl: FormGroup;
    public isFirstTime = true;
    public geoOptions = {
        enableHighAccuracy : false,      // true for "fine" position (GPS), false for "coarse" position (network, et al)
        timeout : 15000,                // maximum milliseconds to wait to return a result (default is "Infinity")
        maximumAge : 60000,             // max age in msecs of an acceptable cached position, zero -> no caching, "Infinity" -> return a cached position
        watchId : null                  // holds handle for watchPosition(), pass as null to clearWatch() to terminate the watch
    };

    public isLeft = new BehaviorSubject<boolean>(false);

    constructor(private navCtrl: NavController,
        private commonService: CommonService,
        private geoLocation: Geolocation,
        private app: App,
        private events: Events,
        private headerService: HeaderService,
        private nearbyService: NearByService,
        public modalCtrl: ModalController,
        public popoverCtrl: PopoverController
    ) {
        this.searchControl = new FormGroup({
            'search': new FormControl(null)
        });

        // this.events.subscribe('TabSelected', (data) => { 
        //     if (data == 0 && !this.isFirstTime )   {
        //         this.navCtrl.popToRoot();
        //         this.searchControl.controls['search'].setValue(null);
        //     }
        //  });

        this.baseUrl = GlobalVariable.BASE_URL;
    }

    ionViewWillEnter() {
        // this.isFirstTime = false;
        this.commonService.presentLoading();
        this.searchControl.reset();
        this.geoLocation.getCurrentPosition(this.geoOptions).then((resLocation) => {
            this.longitude = resLocation.coords.longitude;
            this.latitude = resLocation.coords.latitude;
            this.geoDetails = {
                longitude: this.longitude,
                latitude: this.latitude
            }
            // this.geoDetails = {
            //     longitude: 0.1278,
            //     latitude: 51.5074

            // }
            this.geoDetails = Object.assign({}, this.geoDetails);

            this.nearbyService.postDetails(this.geoDetails).subscribe((response: any) => {
                if (response.error == false) {
                    this.commonService.dismissLoading();
                    this.outlets = response.data;
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
        }, (error) => {
            this.commonService.dismissLoading();
        });

        this.headerSubscription = this.headerService.searchFlag().subscribe((searchFlag) => {
            this.searchOption = searchFlag;
        });

        this.searchSubscription = this.searchControl.valueChanges.debounceTime(700).subscribe(search => {
            if (search.search && search.search != "") {
                this.searchBusiness(search.search);
            } else {
                if (this.masterOutletDetails) {
                    this.outlets = this.masterOutletDetails;
                }
            }
        });
    }

    checkIn(outlet: any, isFromCheckInButton: boolean) {
        this.commonService.presentLoading();
        this.checkInDetails = {
            'businessId': outlet.business_id,
            'branchId': outlet._id
        }
        if (!isFromCheckInButton) {
            this.commonService.dismissLoading();
            this.navCtrl.push(CheckInComponent, { "outlet": outlet, "isFromCheckInButton": isFromCheckInButton });
        } else {
            this.nearbyService.postCheckInDetails(this.checkInDetails)
                .subscribe((res: any) => {
                    this.commonService.dismissLoading();
                    this.navCtrl.push(CheckInComponent, { "outlet": outlet, "isFromCheckInButton": isFromCheckInButton });
                }, error => {
                    this.commonService.dismissLoading();
                });
        }
    }

    searchBusiness(text) {
        if (text && text != "") {
            this.nearbyService.searchBusiness(text, this.longitude, this.latitude).subscribe((response: any) => {
                if (response.error == false) {
                    this.outlets = response.data;
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
        }
    }

    ionViewWillLeave() {
        this.headerSubscription.unsubscribe();
        this.searchSubscription.unsubscribe();
    }


    ionViewDidLeave () {
        console.log("taba laasve");
    }

    ngOnDestroy() {
        this.events.unsubscribe('TabSelected');
    }
}

