import { OneSignal } from '@ionic-native/onesignal';
import { Platform } from 'ionic-angular';
import { Component } from '@angular/core';
import { NavController, NavParams, App, Events } from 'ionic-angular';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { Login } from './login.class';
import { TabsPage } from '../tabs/tabs';
import { RegisterPage } from '../register/register.component';

import { Sql } from '../../providers/sql';
import { CommonService } from '../../providers/common';
import { LoginService } from './login.service';
import { AuthService } from './auth.service';

import { CountryCodes } from '../register/register.class';
import { RegisterService } from '../register/register.service';

import { Device } from '@ionic-native/device';
import { MyCustomersComponent } from '../business-user/my-customers/my-customers.component';
import { ForgotPasswordComponent } from '../forgot-password/forgot-password.component';
import { PromotionsService } from '../promotions/promotions.service';
import { BadgeProvider } from './badge.service';
import { SetPinComponentBusinessUser } from '../business-user/set-pin/set-pin.component';

import { Geolocation } from '@ionic-native/geolocation';
import { PointsPopUpService } from '../business-user/points-popup/points-popup.service';
import { CustomerDetailsService } from '../business-user/customer-details/customer-details.service';
import { SetPinComponent } from '../set-pin/set-pin.component';


@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
})
export class LoginComponent {
    userForm: FormGroup
    loginData: Login;
    currentToken: string;
    countryList: Array<CountryCodes> = [];
    fromRegisterFlag: any = false;
    loadTemplate: boolean = false;
    passcode: string = '';
    oneSignalId: any;
    countryDialCode = '';
    geoOptions = {
        enableHighAccuracy: false,      // true for "fine" position (GPS), false for "coarse" position (network, et al)
        timeout: 15000,                // maximum milliseconds to wait to return a result (default is "Infinity")
        maximumAge: 60000,             // max age in msecs of an acceptable cached position, zero -> no caching, "Infinity" -> return a cached position
        watchId: null                  // holds handle for watchPosition(), pass as null to clearWatch() to terminate the watch
    };

    private longitude: number;
    private latitude: number;
    private geoDetails = {};
    private uuid: any;

    constructor(private commonService: CommonService,
        private _loginService: LoginService,
        private pointsPopUpService: PointsPopUpService,
        private customerDetailsService: CustomerDetailsService,
        private authService: AuthService,
        private registerService: RegisterService,
        private app: App,
        private geoLocation: Geolocation,
        public platform: Platform,
        private oneSignal: OneSignal,
        private navCtrl: NavController,
        private navParams: NavParams,
        private sql: Sql,
        private device: Device,
        public events: Events,
        private promotionService: PromotionsService,
        private badgeProvider: BadgeProvider
    ) {
        this.uuid = this.device.uuid;
        this.userForm = new FormGroup({
            'username': new FormControl(null, [Validators.required, Validators.minLength(10), Validators.maxLength(10), Validators.pattern('[0-9]{10}')]),
            'passcode': new FormControl(null, Validators.required),
            'device_id': new FormControl(this.uuid),
            'onesignalid': new FormControl(null),
            'dialCode': new FormControl(null)
        });
    }

    ionViewWillEnter () {
        if (this.platform.is('cordova')) {
            this.oneSignal.startInit('d5f92180-25cf-44e8-b5ed-7dfaea228513', '421806810556');
            this.oneSignal.getIds().then((data) => {
                this.sql.set('oneSignalId', data.userId);
            });

            this.oneSignal.endInit();
        }

        this.registerService.getCountryCodes().subscribe((list) => {
            this.countryList = list.data;
            this.userForm.controls['dialCode'].setValue(this.countryList[0]._id);
            this.countryDialCode = this.countryList[0].dial_code;
            this.loadTemplate = true;
        }, (err) => {
            this.commonService.toast("Something went wrong");
        });

        this.fromRegisterFlag = this.navParams.get('fromRegister');

        if (this.fromRegisterFlag) {
            this.passcode = this.navParams.get('passcode');
        }

        this.userForm.get('dialCode').valueChanges.subscribe((data) => {
            this.countryList.forEach((element) => {
                if (element._id == data) {
                    this.countryDialCode = element.dial_code;
                }
            });
        });
    }

    getAuthToken () {
        return this.sql.get('token').then((res) => {
            return res;
        });
    }

    login () {
        if (this.userForm.valid) {
            this.commonService.presentLoading();
            this.sql.get('oneSignalId').then((data) => {
                this.loginData = this.userForm.value;
                this.loginData.username = this.countryDialCode + this.userForm.controls['username'].value;
                this.loginData.onesignalid = data;
                this._loginService.login(this.loginData).subscribe((res: any) => {
            
                    if (res.error == false) {
                        if (res.data.accountType == "CUSTOMER") {
                            if (res.data.is_first_time) {
                                this.commonService.dismissLoading();
                                this.navCtrl.push(SetPinComponent);
                            } else {
                                this.commonService.setCustomer(res);
                                this.geoLocation.getCurrentPosition(this.geoOptions).then((resLocation) => {
                                    this.longitude = resLocation.coords.longitude;
                                    this.latitude = resLocation.coords.latitude;
                                    this.geoDetails = {
                                        longitude: this.longitude,
                                        latitude: this.latitude
                                    }

                                    this.geoDetails = Object.assign({}, this.geoDetails);

                                    this.promotionService.getPromotion(this.geoDetails).subscribe((res: any) => {
                                        if (res.error == false) {
                                            this.commonService.dismissLoading();
                                            this.badgeProvider.count.next(res.data.unreadedPromotions);
                                            this.app.getRootNav().setRoot(TabsPage);
                                        } else {
                                            this.commonService.dismissLoading();
                                            this.commonService.toast(res.message);
                                        }
                                    });

                                }, () => {
                                    this.commonService.dismissLoading();
                                });
                            }
                        } else {
                            if (res.data.is_first_time) {
                                this.commonService.dismissLoading();
                                this.navCtrl.push(SetPinComponentBusinessUser);
                            } else {
                                this.commonService.setBusinessUser(res);
                                this.pointsPopUpService.getDewPointRequest().subscribe((response) => {
                                    if (response.error == false && response.data.dewPointRequests.length > 0) {
                                        this.customerDetailsService.updateBusinessHeaderObservable.next({
                                            flag: true,
                                            points: res.data.dew_points
                                        });
                                    }
                                    this.commonService.dismissLoading();
                                    this.app.getRootNav().setRoot(MyCustomersComponent);
                                });
                            }
                        }
                    }
                    else {
                        this.commonService.dismissLoading();
                        this.commonService.toast(res.message);
                    }
                }, (err) => {
                    this.commonService.toast('Something went wrong, Please try again');
                    this.commonService.dismissLoading();
                });
            });
        } else {
            this.commonService.toast('Please fill the form');
        }
    }

    forgotPassword () {
        this.navCtrl.push(ForgotPasswordComponent);
    }

    createAccount () {
        this.navCtrl.push(RegisterPage);
    }
}