import { Component } from '@angular/core';
import { App, NavController } from 'ionic-angular';
import { CommonService } from '../../providers/common';
import { PromotionsService } from './promotions.service';
import { PromotionDetailsComponent } from '../promotion-details/promotion-details.component';
import { LoginComponent } from '../login/login.component';
import { Events } from 'ionic-angular';
import { Geolocation } from '@ionic-native/geolocation';

@Component({
    selector: 'app-promotions',
    templateUrl: './promotions.component.html',
})
export class PromotionsComponent {
    private longitude: number;
    private latitude: number;
    private geoDetails = {};
    promotions: any;
    outletDetails: any;
    businessId: string;
    public header_data = "Promotions";
    geoOptions = {
        enableHighAccuracy: false,      // true for "fine" position (GPS), false for "coarse" position (network, et al)
        timeout: 15000,                // maximum milliseconds to wait to return a result (default is "Infinity")
        maximumAge: 60000,             // max age in msecs of an acceptable cached position, zero -> no caching, "Infinity" -> return a cached position
        watchId: null                  // holds handle for watchPosition(), pass as null to clearWatch() to terminate the watch
    };

    constructor(
        private promotionsService: PromotionsService,
        private commonService: CommonService,
        private geoLocation: Geolocation,
        private navCtrl: NavController,
        private app: App,
        private events: Events
    ) {
    }

    ionViewWillEnter() {
        this.geoLocation.getCurrentPosition(this.geoOptions).then((resLocation) => {
            this.longitude = resLocation.coords.longitude;
            this.latitude = resLocation.coords.latitude;
            this.geoDetails = {
                longitude: this.longitude,
                latitude: this.latitude
            }

            this.geoDetails = Object.assign({}, this.geoDetails);

            this.commonService.presentLoading();
            this.promotionsService.getPromotion(this.geoDetails).subscribe((response: any) => {
                if (response.error == false) {
                    this.promotions = response.data.promotions;
                    this.events.publish('count:updated', response.data.unreadedPromotions);
                    this.commonService.dismissLoading();
                } else {
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
        }, (error) => {
            this.commonService.dismissLoading();
        });
    }

    promotionsDetails(promotions: any) {
        let data = {
            promotionId: promotions._id
        }
        this.promotionsService.readPromotion(data)
            .subscribe((res) => {
            })

        this.navCtrl.push(PromotionDetailsComponent, { 'promotions': promotions });

        // let modalCreate = this.modalCtrl.create(PromotionDetailsComponent, { 'promotions': promotions })
        // modalCreate.present();

        //modalCreate.onDidDismiss(() => {
        //this.app.getRootNav().setRoot(TabsPage,{'tab': 2});

        // this.promotionsService.getPromotion().subscribe((response: any) => {
        //     if (response.error == false) {
        //         this.events.publish('count:updated', response.data.unreadedPromotions);
        //     } else {
        //         this.commonService.toast(response.message);
        //     }
        // }, (error) => {
        // });
        // })
    }

    ionViewWillLeave() {
        console.log("will leave");
    }
}