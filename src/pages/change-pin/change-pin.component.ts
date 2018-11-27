import { NavController } from 'ionic-angular';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { CommonService } from './../../providers/common';
import { ChangePinService } from './change-pin.service';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-pin.component.html',
})
export class ChangePinComponent {
    changePinForm: FormGroup;

    constructor(private commonService:CommonService,
        private changePinService: ChangePinService,
        private navCtrl: NavController
    ) {

        this.changePinForm = new FormGroup({
            'password': new FormControl(null, Validators.required),
            'new_password': new FormControl(null, Validators.required),
            'confirm_password': new FormControl(null, Validators.required)
        });
    }

    changePin() {
        if (this.changePinForm.valid) {
            this.commonService.presentLoading();
            let data = this.changePinForm.value;
            if (data.new_password != data.confirm_password) {
                this.commonService.toast('New Pin & Confirm pin does not match');
                this.commonService.dismissLoading();
            } else {
                this.changePinService.changePassword(data).subscribe(res => {
                    if (res.error == false) {
                        this.commonService.toast('Password changed successfully.');
                        this.commonService.dismissLoading();
                        this.navCtrl.pop()
                    } else {
                        this.commonService.dismissLoading();
                        this.commonService.toast(res.message);
                    }
                }, (err) => {
                    this.commonService.toast('Something went wrong, Please try again');
                    this.commonService.dismissLoading();
                });
            }
        }
        else {
            this.commonService.toast('Please fill the form');
        }
    }
}   