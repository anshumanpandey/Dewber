import { Component } from "@angular/core";
import {
  App,
  PopoverController,
  ViewController,
  NavController,
  ModalController,
  PopoverOptions
} from "ionic-angular";
import { Sql } from "../../providers/sql";
import { LoginComponent } from "../login/login.component";
import { ProfileService } from "./profile.service";
import { CommonService } from "../../providers/common";
import { NotificationSettingComponent } from "../notification-setting/notification-setting.component";
import { ChangePinComponent } from "../change-pin/change-pin.component";
import { RegisterPage } from "../register/register.component";
import { MyCustomersComponent } from "../business-user/my-customers/my-customers.component";
import { AuthService } from "../login/auth.service";
import { SendFeedBackComponent } from "../feedback/feedback.component";
import { GlobalVariable } from "../../app/app.component";

@Component({
  selector: "app-profile",
  templateUrl: "profile.component.html"
})
export class ProfilePage {
  public switchAccount: any;
  public switchAccountId = {};
  public switchAccountOption: boolean = false;
  public isDefaultAccount: boolean;
  public firstname: string = null;
  public lastname: string = null;
  public profile_picture: string = null;
  public profileDetails: any;
  templateShow: boolean = false;
  public updateCustomerLoginDetails: any;

  public baseURL = GlobalVariable.BASE_URL;
  constructor(
    private app: App,
    private viewCtrl: ViewController,
    private sql: Sql,
    private navCtrl: NavController,
    private profileService: ProfileService,
    private commonService: CommonService,
    private modelCtrl: ModalController,
    private authService: AuthService,
    private popOverCtrl: PopoverController
  ) {
    this.sql.get("switchAccountId").then(data => {
      this.switchAccount = data;
    });
    this.sql.get("switchAccountOption").then(data => {
      if (data != undefined && data != null) {
        if (data == "true") {
          this.switchAccountOption = true;
        } else {
          this.switchAccountOption = false;
        }
      }
    });
    this.sql.get("firstname").then(data => {
      this.firstname = data;
    });
    this.sql.get("lastname").then(data => {
      this.lastname = data;
    });
    this.sql.get("profile_picture").then(data => {
      this.profile_picture = data;
    });

    this.profileService.getProfileDetails().subscribe((res: any) => {
      this.profileDetails = res.data;
      this.isDefaultAccount = res.data.isDefaultAccount;
      this.sql.set("firstname", res.data.firstname);
      this.sql.set("lastname", res.data.lastname);
      this.sql.set("profile_picture", res.data.profile_picture);
      this.sql.set("isDefaultAccount", res.data.isDefaultAccount);
      this.templateShow = true;
    });
  }

  logout() {
    this.viewCtrl.dismiss();
    this.commonService
      .logout()
      .then(() => {
        this.app.getRootNav().setRoot(LoginComponent);
      })
      .catch(() => {
        this.commonService.toast("Something went wrong");
      });
  }

  editProfile() {
    this.viewCtrl.dismiss();
    let profileModal = this.modelCtrl.create(RegisterPage, {
      referralCode: false
    });
    profileModal.present();
  }

  notificationSetting() {
    this.viewCtrl.dismiss();
    this.navCtrl.push(NotificationSettingComponent);
  }

  switchLogin() {
    this.viewCtrl.dismiss();
    this.switchAccountId = {
      switchAccountId: this.switchAccount
    };
    this.profileService
      .switchUser(this.switchAccountId)
      .subscribe((res: any) => {
        if (res.error == false) {
          this.commonService.setBusinessUser(res);
          this.commonService.dismissLoading();
          if (res.data.is_first_time) {
            this.app.getRootNav().setRoot(LoginComponent);
          } else {
            this.app.getRootNav().setRoot(MyCustomersComponent);
          }
        } else {
          this.commonService.dismissLoading();
          this.commonService.toast(res.message);
        }
      });
  }

  changeDefault() {
    this.updateCustomerLoginDetails = {
      isDefaultAccount: this.isDefaultAccount
      // 'firstname': this.firstname,
      // 'lastname': this.lastname,
      // 'phone_no': this.phone_no
    };
    this.commonService.presentLoading();
    this.profileService
      .changeDefaultLogin(this.updateCustomerLoginDetails)
      .subscribe(
        (res: any) => {
          if (res.error == false) {
            this.commonService.dismissLoading();
            this.sql.set("isDefaultAccount", this.isDefaultAccount.toString());
            this.commonService.toast(res.message);
          } else {
            this.commonService.dismissLoading();
            this.commonService.toast(res.message);
          }
        },
        error => {
          this.commonService.dismissLoading();
          this.commonService.toast(error);
        }
      );
  }

  changePin() {
    this.viewCtrl.dismiss();
    this.navCtrl.push(ChangePinComponent);
  }

  sendFeedback() {
    // popover.present();
    this.viewCtrl.dismiss();
    let popOverOptions: PopoverOptions = {
      cssClass: "feedback-pop-over"
    };
    let feedbackPop = this.popOverCtrl.create(
      SendFeedBackComponent,
      {},
      popOverOptions
    );
    feedbackPop.present();
  }
}
