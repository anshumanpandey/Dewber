import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { SetPin } from './set-pin.classes';
import { CommonService } from '../../../providers/common';
import { SetPinServiceBusinessUser } from './set-pin.service';
import { NavParams, App } from 'ionic-angular';
import { Sql } from '../../../providers/sql';
import { AuthService } from '../../login/auth.service';
import { MyCustomersComponent } from '../my-customers/my-customers.component';
import { LoginComponent } from '../../login/login.component';

@Component({
    selector: 'app-set-pin',
    templateUrl: './set-pin.component.html',
})
export class SetPinComponentBusinessUser {
    setPinForm: FormGroup;
    private otp: any;
    private setPin: SetPin;
    constructor(private commonService: CommonService,
        private setPinService: SetPinServiceBusinessUser,
        private navParams: NavParams,
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
                    this.commonService.setBusinessUser(res);
                    this.commonService.dismissLoading();
                    this.app.getRootNav().setRoot(MyCustomersComponent);
                } else {
                    this.commonService.dismissLoading();
                    this.commonService.toast(res.message);
                    this.app.getRootNav().setRoot(LoginComponent); 
                }
            });
        } else {
            this.commonService.toast("Enter valid PIN");
        }

    }
}