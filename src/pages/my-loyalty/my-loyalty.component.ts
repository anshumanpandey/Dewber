import { Component } from '@angular/core';
import { MyLoyaltyDewRewardComponent } from './dew-reward/dew-reward.component';
import { MyLoyaltyDewPointComponent } from './dew-point/dew-point.component';
import { Events, NavController } from 'ionic-angular';

@Component({
    selector: 'app-my-loyalty',
    templateUrl: './my-loyalty.component.html',
})
export class MyLoyaltyComponent {
    dewRewardAndPointRequest: Object;
    header_data: string = null;
    dewRewardComponent: any = MyLoyaltyDewRewardComponent;
    dewPointComponent: any = MyLoyaltyDewPointComponent;

    constructor(private events: Events,
    private navCtrl: NavController) {
        this.header_data = "My Loyalty";
    }

    ionViewWillLeave() {
        this.events.publish('leaving');
        // this.navCtrl.popToRoot();
    }

}