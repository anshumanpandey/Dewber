import { RegisterService } from './../../register/register.service';
import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { UserRegistrationService } from './user-registration.service';
import { App, NavController } from 'ionic-angular';
import { CommonService } from '../../../providers/common';
import { Sql } from '../../../providers/sql';
import { LoginComponent } from '../../login/login.component';
import { CustomerDetailsComponent } from '../customer-details/customer-details.component';

@Component({
    selector: 'app-user-registration',
    templateUrl: './user-registration.component.html',
})
export class UserRegistrationComponent {
    public registerData: any;
    public registerForm: FormGroup;
    public countryList: any;
    public loadTemplate: boolean = false;
    public firstname: string;
    public lastname: string;
    public countryDialCode: any;


    constructor(private _registerService: RegisterService,
        private userRegistrationService: UserRegistrationService,
        private commonService: CommonService,
        private navCtrl: NavController,
        private app: App,
        private sql: Sql) {

        this.registerForm = new FormGroup({
            'firstname': new FormControl(null, Validators.required),
            'lastname': new FormControl(null, Validators.required),
            'email': new FormControl(null, [Validators.email]),
            'phone_no': new FormControl(null, [Validators.required, Validators.pattern('[1-9]{1}[0-9]{9}'), Validators.minLength(10), Validators.maxLength(10)]),
            'dialCode': new FormControl(null)
        });

        this.registerForm.get('dialCode').valueChanges.subscribe((data) => {
            this.countryList.forEach((element) => {
                if (element._id == data) {
                    this.countryDialCode = element.dial_code;
                }
            });
        });

        this._registerService.getCountryCodes().subscribe((list) => {
            this.countryList = list.data;
            this.sql.get('countrycode').then((data) => {
                this.countryList.forEach((element) => {
                    if (element.code == data) {
                        this.countryDialCode = element.dial_code;
                        this.registerForm.controls['dialCode'].setValue(element._id);
                    }
                });
            });
        }, (err) => {
            this.commonService.toast("Something went wrong");
        });

        this.loadTemplate = true;
    }

    getFormValidationErrors (key) {
        const controlErrors: ValidationErrors = this.registerForm.get(key).errors;
        if (controlErrors != null) {
            return true;
        }
    }

    getCountryDialCode(data) {
      this.countryList.forEach((element) => {
        if (element._id == data) {
            this.countryDialCode = element.dial_code;
        }
      });
    }

    createAccount() {
        this.registerData = this.registerForm.value;
        this.getCountryDialCode(this.registerForm.controls['phone_no'].value);
        this.registerData.full_phone =  this.countryDialCode + this.registerForm.controls['phone_no'].value;
        if (this.registerForm.valid) {
            this.commonService.presentLoading();
            this.userRegistrationService.saveUser(this.registerData).subscribe((response: any) => {
                if (response.error == false) {
                    this.commonService.dismissLoading();
                    this.navCtrl.pop();
                    this.commonService.toast(response.message);
                    this.navCtrl.push(CustomerDetailsComponent, { customerDetail: response.data, fromAdd: true });

                } else {
                    this.commonService.dismissLoading();
                    this.commonService.toast(response.message);
                }
            }, (error) => {
                this.commonService.logout().then(() => {
                    this.commonService.dismissLoading();
                    this.app.getRootNav().setRoot(LoginComponent);
                  }).catch ( () => {
                    this.commonService.dismissLoading();        
                    this.commonService.toast('Something went wrong');
                  });
            });
        } else {
            if (this.getFormValidationErrors('phone_no')) {
                this.commonService.toast("Please enter valid mobile number (10 digits allowed).");
            } else {
                this.commonService.toast("Please enter valid data in all the field.");
            }
        }
    }
}