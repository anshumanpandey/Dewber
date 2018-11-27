import { Component } from '@angular/core';
import { NavParams, ModalController } from 'ionic-angular';
import { GlobalVariable } from '../../../app/app.component';
import { LocationDetailsComponent } from './../../location-details/location-details.component';

@Component({
    selector: 'app-info-map',
    templateUrl: './info-map.component.html',
})
export class InfoMapComponent {

    public outletDetails: any;
    public phoneNo: boolean = false;
    public branch_website_url: boolean = false;
    public branch_email: boolean = false;
    public baseUrl: any;

    constructor(private modalCtrl: ModalController,
        private navParams: NavParams,
    ) {
        this.baseUrl = GlobalVariable.BASE_URL
        this.outletDetails = this.navParams.get('outlet');

        // check phone no 
        if (this.outletDetails.branch_phone_no == "" && this.outletDetails.c_code_phone_no == "undefined") {
            this.phoneNo = false;
        } else {
            this.phoneNo = true;
        }
        // check web-url
        if (this.outletDetails.branch_website_url == "") {
            this.branch_website_url = false;
        } else {
            this.branch_website_url = true;
        }
        // check email
        if (this.outletDetails.branch_email == "") {
            this.branch_email = false;
        } else {
            this.branch_email = true;
        }

    }

    googleMap () {
        let locationModal = this.modalCtrl.create(LocationDetailsComponent, {
            'location': {
                'latitude': this.outletDetails.branch_latitude,
                'longitude': this.outletDetails.branch_longitude
            }
            , 'name': this.outletDetails.branch_name
        });
        locationModal.present();
    }
}