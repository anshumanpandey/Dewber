import { HeaderService } from './../header/header.service';
import { Component } from '@angular/core';
import { NavController, App, ModalController, PopoverController, Events } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';
import { CommonService } from './../../providers/common';


import { GlobalVariable } from '../../app/app.component';
import { CheckInComponent } from '../checkin/checkin.component';
import { LoginComponent } from '../login/login.component';
import { ProfilePage } from '../profile/profile.component';


import { QRCodeComponent } from '../qr-code/qr-code.component';
import { RegularsService } from './regulars.service';
import { NearByService } from '../nearby/nearby.service';
import { Subscription } from 'rxjs';
import { FormGroup, FormControl } from '@angular/forms';

@Component({
    selector: 'app-regulars',
    templateUrl: './regulars.component.html',
})
export class RegularsComponent {
    private longitude: number;
    private latitude: number;
    private geoDetails = {};
    private outlets: Array<any> = [];
    private subscription: Subscription;
    private searchSubscription: Subscription;

    public checkInDetails = {};
    public baseUrl = GlobalVariable.BASE_URL;
    public header_data: string = null;
    
    public searchOption: boolean = false;
    public searchControl: FormGroup;
    public masterOutletDetails: Array<any>;
    public isFirstTime = true;

    constructor(private navCtrl: NavController,
        private commonService: CommonService,
        private geoLocation: Geolocation,
        private events: Events,
        private app: App,
        private headerService: HeaderService,
        private regularsService: RegularsService,
        public modalCtrl: ModalController,
        public popoverCtrl: PopoverController,
        private nearbyService: NearByService
    ) {
        this.header_data = "Regular";

        this.searchControl = new FormGroup({
            'search': new FormControl(null)
        });

        // this.events.subscribe('TabSelected', (data) => {
        //     if (data == 1 && !this.isFirstTime) {
        //         this.navCtrl.popToRoot();
        //         this.searchControl.controls['search'].setValue(null);
        //     }
        // });
    }

    ionViewWillEnter() {
        // this.navCtrl.popToRoot();
        this.isFirstTime = false;
        this.searchControl.reset();
        this.commonService.presentLoading();
        this.geoLocation.getCurrentPosition().then((resLocation) => {
            this.longitude = resLocation.coords.longitude;
            this.latitude = resLocation.coords.latitude;
            this.geoDetails = {
                longitude: this.longitude,
                latitude: this.latitude
            }
            // this.geoDetails = {
            // longitude: 72.5520097,
            // latitude: 23.064327199999997

            // }
            this.geoDetails = Object.assign({}, this.geoDetails);

            this.regularsService.postDetails(this.geoDetails).subscribe((response: any) => {
                if (response.error == false) {
                    this.outlets = response.data;
                    this.masterOutletDetails = response.data;
                    this.commonService.dismissLoading();
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

        this.subscription = this.headerService.searchFlag().subscribe((searchFlag) => {
            this.searchOption = searchFlag;
        });

        this.searchSubscription = this.searchControl.valueChanges.debounceTime(700).subscribe(search => {
            if (this.masterOutletDetails && this.masterOutletDetails.length > 0) {
                if (search.search && search.search != "") {
                    const searchText = search.search.toUpperCase();

                    this.outlets = this.masterOutletDetails.filter(item => {
                        return item.businessInfo.business_name.toUpperCase().indexOf(searchText) > -1
                    });
                } else {
                    this.outlets = this.masterOutletDetails;
                }
            } else {
                this.outlets = this.masterOutletDetails;
            }
        });
    }

    setting() {
        this.commonService.logout().then(() => {
            this.app.getRootNav().setRoot(LoginComponent);
        }).catch(() => {
            this.commonService.toast('Something went wrong');
        });
    }
    profile() {
        let popover = this.popoverCtrl.create(ProfilePage);
        popover.present();
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

    qrScanner() {
        let qrModal = this.modalCtrl.create(QRCodeComponent);
        qrModal.present();
    }

    ionViewWillLeave() {
        this.subscription.unsubscribe();
        this.searchSubscription.unsubscribe();
    }

    ngOnDestroy() {
        this.events.unsubscribe('TabSelected');
    }
}

