import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonService } from '../../providers/common';
import { NavParams, App } from 'ionic-angular';
import { ReSetPin } from './reset-pin.classes';
import { ReSetPinService } from './reset-pin.service';
import { LoginComponent } from '../login/login.component';

@Component({
    selector: 'app-reset-pin',
    templateUrl: './reset-pin.component.html',
})
export class ReSetPinComponent {
    reSetPinForm: FormGroup;
    private otp: any;
    private reSetPin: ReSetPin;
    
    constructor(private commonService: CommonService,
        private setPinService: ReSetPinService,
        private navParams: NavParams,
        private app: App
    ) {

        this.reSetPinForm = new FormGroup({
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
        this.reSetPin = this.reSetPinForm.value;
        if (this.reSetPinForm.valid) {
            this.commonService.presentLoading();
            this.setPinService.setPin(this.reSetPin).subscribe((res: any) => {
                if (res.error == false) {
                    this.commonService.dismissLoading();
                    this.app.getRootNav().setRoot(LoginComponent);
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