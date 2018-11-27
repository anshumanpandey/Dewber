import { Component } from '@angular/core';
import { NotificationSettingService } from './notification-setting.service';
import { CommonService } from '../../providers/common';

@Component({
    selector: 'app-notification-setting',
    templateUrl: './notification-setting.component.html',
})
export class NotificationSettingComponent {
    private notificationFrequency: any = "hourly";
    private notificationSubscription: boolean = false;
    notificationData = {};
    constructor(
        private notificationService: NotificationSettingService,
        private commonService: CommonService
    ) {

    }
    // onChange(notificationFrequency) {
    //     console.log(notificationFrequency);
    // }
   
    // notificationSetting() {
    //     this.notificationData = {
    //         'notification_frequency': this.notificationFrequency,
    //         'newsletter_subscription': this.notificationSubscription
    //     }
    //     this.notificationService.updateSetting(this.notificationData)
    //         .subscribe((res: any) => {
    //             this.commonService.toast(res.message);
    //         });
    // }

}