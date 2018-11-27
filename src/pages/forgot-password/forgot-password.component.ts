import { Component } from '@angular/core';
import { NavController } from 'ionic-angular';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonService } from '../../providers/common';
import { ForgotPasswordService } from './forgot-password.service';
import { ForgotPassword } from './forgot-password.classes';
import { ReSetPinComponent } from '../reset-pin/reset-pin.component';
import { RegisterService } from '../register/register.service';

@Component({
    selector: 'app-forgot-password',
    templateUrl: './forgot-password.component.html',
})

export class ForgotPasswordComponent {
    loadTemplate: boolean = false;
    private forgotPasswordForm: FormGroup;
    forgotPasswordData = {
        'phone_no' : ''
    };
    countryList: any;
    countryDialCode: any;

    constructor(
        private _registerService: RegisterService,
        private forgotPasswordService: ForgotPasswordService,
        private commonService: CommonService,
        private navCtrl: NavController
    ) {
        this.forgotPasswordForm = new FormGroup({
            'phone_no': new FormControl(null, Validators.required),
            'dialCode' : new FormControl(null)
        });

    }

    ionViewWillEnter(){

        this._registerService.getCountryCodes().subscribe((list) => {
            this.countryList = list.data;
            this.forgotPasswordForm.controls['dialCode'].setValue(this.countryList[0]._id);
            this.countryDialCode = this.countryList[0].dial_code;
            this.loadTemplate = true;
        }, (err) => {
            this.commonService.toast("Something went wrong");
        });

        this.forgotPasswordForm.get('dialCode').valueChanges.subscribe((data) => {
            this.countryList.forEach((element) => {
                if (element._id == data) {
                    this.countryDialCode = element.dial_code;
                }
            });
        });
    }

    forgotPassword () {
        const forgotData = this.forgotPasswordForm.value;
        this.forgotPasswordData.phone_no = this.countryDialCode + forgotData.phone_no
        this.forgotPasswordService.forgotPassword(this.forgotPasswordData).subscribe((res: any) => {
            if (res.error == false) {
                this.commonService.toast(res.message);
                this.navCtrl.push(ReSetPinComponent, { 'otp': res.data.mobile_otp });
            } else {
                this.commonService.toast(res.message);
            }
        })
    }
}