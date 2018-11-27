import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { CommonService } from '../../providers/common';
import { NavParams, NavController } from 'ionic-angular';
import { Pin } from './pin.classes';
import { PinService } from './pin.service';

@Component({
    selector: 'app-pin',
    templateUrl: './pin.component.html',
})

export class PinComponent {
    pinForm: FormGroup;
    otp: any;
    pin: Pin;

    constructor(private commonService: CommonService,
        private navCtrl: NavController,
        private pinService: PinService,
        private navParams: NavParams
    ) {

        this.pinForm = new FormGroup({
            'otp': new FormControl(null, Validators.required),
        });
        const paramData = this.navParams.get('data');
        this.pin = paramData.profile;
        this.otp = paramData.otp;

    }

    ionViewWillEnter() {
        if (this.commonService.getenv()) {
           this.commonService.toast(this.otp); 
        }
    }

    confirmPin() {
        this.pin.otp = this.pinForm.value.otp;
        if (this.pinForm.valid) {
            this.commonService.presentLoading();
            this.pinService.confirmPin(this.pin).subscribe((res: any) => {
                if (res.error == false) {
                    this.commonService.dismissLoading();
                    this.commonService.toast(res.message);
                    this.navCtrl.pop();
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