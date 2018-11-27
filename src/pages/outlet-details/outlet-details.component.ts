import { Component, ViewChild } from '@angular/core';

import { DewPointComponent } from './dew-point/dew-point.component';
import { DewRewardComponent } from './dew-reward/dew-reward.component';
import { InfoMapComponent } from './info-map/info-map.component';
import { NavParams, Events, Content } from 'ionic-angular';

@Component({
    templateUrl: './outlet-details.component.html',
})
export class OutletDetailsComponent {

    @ViewChild('headerComp') headerComponent: any;
    @ViewChild(Content) content: Content;

    dewRewardAndPointRequest: Object;
    header_data: string = null;
    dewRewardComponent = DewRewardComponent;
    dewPointComponent = DewPointComponent;
    infoMapComponent = InfoMapComponent;
    showHeader = true;
    // tabBarElement: any;
    branchId: string;

    constructor(private navParams: NavParams,
        private event: Events) {
        this.dewRewardAndPointRequest = { "outlet": this.navParams.get('outlet') };
        console.log(this.dewRewardAndPointRequest);
        this.header_data = this.dewRewardAndPointRequest["outlet"].branch_name;
        this.branchId = this.dewRewardAndPointRequest["outlet"]._id;
        // this.tabBarElement = document.querySelector('.outlet-details .tabbar.show-tabbar');

        this.event.subscribe('headerChange', (data) => {
            this.showHeader = data;

            // if (data) {
            //     //this.tabBarElement.style.display = 'flex';
            //     this.headerComponent.navBar.setHidden(true)
            // } else {
            //      //this.tabBarElement.style.display = 'none';
            //      this.headerComponent.navBar.setHidden(false)
            // }

            this.content.resize();
        })
    }
}