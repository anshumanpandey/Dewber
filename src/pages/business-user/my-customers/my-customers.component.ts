import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MyCustomerService } from './my-customer.service';
import * as moment from 'moment';
import { NavController, App } from 'ionic-angular';
import { UserRegistrationComponent } from '../user-registration/user-registration.component';
import { CustomerDetailsComponent } from '../customer-details/customer-details.component';
import { GlobalVariable } from '../../../app/app.component';
import { CommonService } from '../../../providers/common';
import { LoginComponent } from '../../login/login.component';
import { Subscription } from 'rxjs';

@Component({
    selector: 'app-my-customers',
    templateUrl: './my-customers.component.html',
})
export class MyCustomersComponent{
    public searchComplete: any;
    public searchControl: any;
    public myCustomerDetails: any;
    public baseUrl = GlobalVariable.BASE_URL;
    public header_data: any;

    public masterCustomerDetails: any;
    public term: string = '';
    public isResultSearched: boolean = false;
    public searchSubscription: Subscription;

    constructor(
        private navCtrl: NavController,
        private myCustomerService: MyCustomerService,
        private commonService: CommonService,
        private app: App
    ) {
        this.searchControl = new FormGroup({
            'search': new FormControl(null)
        });
    }

    convertDate(date) {
        return moment(date).fromNow();
    }

    ionViewWillEnter() {
        this.searchControl.controls['search'].reset();
        this.commonService.presentLoading();
        this.myCustomerService.getCustomerDetails().subscribe((response) => {
            if (!response.error) {
                this.myCustomerDetails = response.data;
                this.masterCustomerDetails = response.data;
                this.commonService.dismissLoading();
            }
            else {
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
    }

    ionViewDidEnter() {
        this.searchSubscription = this.searchControl.valueChanges.debounceTime(900).subscribe((search) => {
            this.searchcustomer(search.search);
        });
    }

    addCustomer() {
        this.navCtrl.push(UserRegistrationComponent);
    }

    getCustomerDetails(customerId: string) {
        this.commonService.presentLoading();
        this.myCustomerService.getDetails(customerId).subscribe((res) => {
            if (res.error == false) {
                this.navCtrl.push(CustomerDetailsComponent, { customerDetail: res.data, fromAdd: false });
                this.commonService.dismissLoading();
            } else {
                this.commonService.toast(res.message);
                this.commonService.dismissLoading();
            }
        })
    }


    searchcustomer(searchText) {
        // this.commonService.presentLoading();
        if (searchText && searchText != "") {
            this.myCustomerService.searchCustomer(searchText).subscribe((response: any) => {
                if (response.error == false) {
                    // this.commonService.dismissLoading();
                    this.myCustomerDetails = response.data;
                    this.isResultSearched = true;
                } else {
                    // this.commonService.dismissLoading();
                    this.commonService.toast(response.message);
                }
            }, (error) => {
                this.commonService.logout().then(() => {
                    // this.commonService.dismissLoading();
                    this.app.getRootNav().setRoot(LoginComponent);
                }).catch(() => {
                    // this.commonService.dismissLoading();        
                    this.commonService.toast('Something went wrong');
                });
            });
        } else {
            this.isResultSearched = false;
            
            // this.commonService.dismissLoading();
            this.ionViewWillEnter();
        }
    }

    ionViewWillLeave() {
        this.searchSubscription.unsubscribe();
    }

}   