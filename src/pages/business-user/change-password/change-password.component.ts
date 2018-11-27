import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { App, NavController } from 'ionic-angular';
import { ChangePasswordService } from './change-password.service';
import { MyCustomersComponent } from '../my-customers/my-customers.component';
import { CommonService } from '../../../providers/common';

@Component({
    selector: 'app-change-password',
    templateUrl: './change-password.component.html',
})
export class ChangePasswordComponent {
    changePasswordForm: FormGroup;

    constructor(private commonService: CommonService,
        private app: App,
        private navCtrl: NavController,
        private changePasswordService: ChangePasswordService) {

        this.changePasswordForm = new FormGroup({
            'password': new FormControl(null, Validators.required),
            'new_password': new FormControl(null, Validators.required),
            'confirm_password': new FormControl(null, Validators.required)
        });
    }

    changePassword() {
        if (this.changePasswordForm.valid) {
            this.commonService.presentLoading();
            let data = this.changePasswordForm.value;
            if (data.new_password != data.confirm_password) {
                this.commonService.toast('New password & Confirm password does not match');
                this.commonService.dismissLoading();
            } else {
                this.changePasswordService.changePassword(data).subscribe(res => {
                    if (res.error == false) {
                        this.commonService.toast('Password changed successfully.');
                        this.commonService.dismissLoading();
                        this.navCtrl.pop()
                        this.app.getRootNav().setRoot(MyCustomersComponent);
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