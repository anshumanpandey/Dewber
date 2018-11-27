import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonService } from '../../providers/common';
import { SetPinService } from './set-pin.service';
import { NavParams, App, NavController } from 'ionic-angular';
import { Sql } from '../../providers/sql';
import { AuthService } from '../login/auth.service';
import { TabsPage } from '../tabs/tabs';
import { MyCustomersComponent } from '../business-user/my-customers/my-customers.component';
import { SetPin } from './set-pin.classes';

@Component({
    selector: 'app-set-pin',
    templateUrl: './set-pin.component.html',
})
export class SetPinComponent {
    setPinForm: FormGroup;
    loginStatus: boolean;
    
    private otp: any;
    private setPin: SetPin;

    constructor(private commonService: CommonService,
        private setPinService: SetPinService,
        private navParams: NavParams,
        private navCtrl:NavController,
        private sql: Sql,
        private authService: AuthService,
        private app: App
    ) {

        this.setPinForm = new FormGroup({
            'otp': new FormControl(null, Validators.required),
            'password': new FormControl(null, [Validators.required, Validators.pattern('[0-9]{4}')]),
            'confirm_password': new FormControl(null, [Validators.required, Validators.pattern('[0-9]{4}')])
        });
        this.otp = this.navParams.get('otp');

        this.loginStatus = this.authService.getLoginStatus();
    }

    ionViewWillEnter() {
        if (this.commonService.getenv()) {
           this.commonService.toast(this.otp); 
        }
    }

    changePin() {
        this.setPin = this.setPinForm.value;
        if (this.setPinForm.valid) {
            this.commonService.presentLoading();
            this.setPinService.setPin(this.setPin).subscribe((res: any) => {
                if (res.error == false) {
                    this.commonService.dismissLoading();
                    this.navCtrl.pop();

                    if (res.data.accountType == "CUSTOMER") {
                        this.commonService.setCustomer(res);
                        this.app.getRootNav().setRoot(TabsPage);
                    }
                } else {
                    this.commonService.dismissLoading();
                    this.commonService.toast(res.message);
                }

            });
        } else {
            this.commonService.toast("Enter valid PIN");
        }

    }
}