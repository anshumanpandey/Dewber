import { Component } from '@angular/core';
import { NavParams } from 'ionic-angular';
import { GlobalVariable } from '../../../../app/app.component';

@Component({
    selector: 'app-dew-reward-details',
    templateUrl: './dew-reward-details.component.html',
})
export class DewRewardDetailsComponent {
    private header_data: string = "My Loyalty"
    private dewReward: any;
    private baseUrl = GlobalVariable.BASE_URL;
    constructor(
        private navParams: NavParams
    ) {
        this.dewReward = this.navParams.get('dewReward');
    }

}