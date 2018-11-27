import { Component, ChangeDetectorRef, ViewChild } from '@angular/core';

import { NearByComponent } from '../nearby/nearby.component';
import { PromotionsComponent } from '../promotions/promotions.component';
import { RegularsComponent } from '../regulars/regulars.component';
import { MyLoyaltyComponent } from '../my-loyalty/my-loyalty.component';
import { PromotionsService } from '../promotions/promotions.service';
import { Events, NavParams, Tabs } from 'ionic-angular';
import { BadgeProvider } from '../login/badge.service';
import { Geolocation } from '@ionic-native/geolocation';


@Component({
  templateUrl: 'tabs.html'
})
export class TabsPage {
  @ViewChild('myTabs') tabRef: Tabs;

  private longitude: number;
  private latitude: number;
  private geoDetails = {};
  private promotion_badge: any;
  nearBy = NearByComponent;
  regularsTabs = RegularsComponent;
  tab3Root = PromotionsComponent;
  tab4Root = MyLoyaltyComponent;
  tabToShow: number = 0;
  activeTab: any;
  geoOptions = {
    enableHighAccuracy: false,      // true for "fine" position (GPS), false for "coarse" position (network, et al)
    timeout: 15000,                // maximum milliseconds to wait to return a result (default is "Infinity")
    maximumAge: 60000,             // max age in msecs of an acceptable cached position, zero -> no caching, "Infinity" -> return a cached position
    watchId: null                  // holds handle for watchPosition(), pass as null to clearWatch() to terminate the watch
  };

  constructor(private promotionService: PromotionsService,
    private event: Events,
    private geoLocation: Geolocation,
    private detectorRef: ChangeDetectorRef,
    private navParams: NavParams
  ) {
    this.activeTab = this.navParams.get("tab") ? this.navParams.get("tab") : 0;
    this.geoLocation.getCurrentPosition(this.geoOptions).then((resLocation) => {
      this.longitude = resLocation.coords.longitude;
      this.latitude = resLocation.coords.latitude;
      this.geoDetails = {
        longitude: this.longitude,
        latitude: this.latitude
      }

      this.geoDetails = Object.assign({}, this.geoDetails);
      this.promotionService.getPromotion(this.geoDetails).subscribe((res: any) => {
        if (res.error == false) {
          this.promotion_badge = res.data.unreadedPromotions;
        }
      });
    });
    this.event.subscribe('count:updated', _badgeValue => {
      this.promotion_badge = _badgeValue;
      if (!this.detectorRef['destroyed']) {
        this.detectorRef.detectChanges();
      }
    });

    //this.tabRef.
  }

  tabChanged(index: number) {
    this.event.publish('TabSelected', index);
  }

  ionViewDidLeave() {
    console.log("tabs lave");
  }
}
