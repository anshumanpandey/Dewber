import { EditProfileService } from './../edit-profile/edit-profile.service';
import { Component } from '@angular/core';
import { App, ViewController, NavController } from 'ionic-angular';
import { Sql } from '../../../providers/sql';
import { CommonService } from '../../../providers/common';
import { ChangePasswordComponent } from '../change-password/change-password.component';
import { LoginComponent } from '../../login/login.component';
import { BusinessUserProfileService } from './profile.service';
import { TabsPage } from '../../tabs/tabs';
import { AuthService } from '../../login/auth.service';
import { EditProfileComponent } from '../edit-profile/edit-profile.component';
import { GlobalVariable } from '../../../app/app.component';

@Component({
  selector: "app-profile",
  templateUrl: "profile.component.html"
})

export class BusinessUserProfilePage {
  private user_full_name: any = null;
  private phone_no: any = null;
  private switchAccount: any;
  private switchAccountId = {};
  private isDefaultAccount: boolean;
  private switchAccountOption: boolean = false;
  private firstname: string = null;
  private lastname: string = null;
  private updateBusinessLoginDetails: any;
  profile_picture: any;
  baseURL = GlobalVariable.BASE_URL;
  profileDetails: any;

  constructor(
    private app: App,
    private viewCtrl: ViewController,
    private sql: Sql,
    private navCtrl: NavController,
    private profileService: BusinessUserProfileService,
    private commonService: CommonService,
    private authService: AuthService,
    private edtProfileService: EditProfileService
  ) {

    this.edtProfileService.getProfileDetails().subscribe((res: any) => {
      this.profileDetails = res.data;
      this.sql.set('firstname', res.data.firstname);
      this.sql.set('lastname', res.data.lastname);
      this.sql.set('profile_picture', res.data.profile_picture);
      this.sql.set('isDefaultAccount', res.data.isDefaultAccount);

    });
    this.sql.get('isDefaultAccount').then((data) => {
      if (data != undefined && data != null) {
        if (data == "true") {
          this.isDefaultAccount = true;
        } else {
          this.isDefaultAccount = false;
        }
      }
    });

    this.sql.get('firstname').then((data) => {
      this.firstname = data;
    });

    this.sql.get('lastname').then((data) => {
      this.lastname = data;
    });

    this.sql.get('phone_no').then((data) => {
      this.phone_no = data;
    });

    this.sql.get('switchAccountId').then((data) => {
      this.switchAccount = data;
    });

    this.sql.get('switchAccountOption').then((data) => {
      if (data != undefined && data != null) {
        if (data == "true") {
          this.switchAccountOption = true;
        } else {
          this.switchAccountOption = false;
        }
      }
    });

    this.sql.get('profile_picture').then((data) => {
      this.profile_picture = data;
    });

    this.sql.get('user_full_name').then((data) => {
      if (data != undefined && data != null) {
        this.user_full_name = data;
      }
    });

  }

  changeDefault() {
    this.updateBusinessLoginDetails = {
      'isDefaultAccount': this.isDefaultAccount
      // 'firstname': this.firstname,
      // 'lastname': this.lastname,
      // 'phone_no': this.phone_no
    }
    this.commonService.presentLoading();
    this.profileService.changeDefaultLogin(this.updateBusinessLoginDetails)
      .subscribe((res: any) => {
        if (res.error == false) {
          this.commonService.dismissLoading();
          this.sql.set("isDefaultAccount", this.isDefaultAccount.toString());
          this.commonService.toast(res.message);
        } else {
          this.commonService.dismissLoading();
          this.commonService.toast(res.message);
        }

      }, (error) => {
        this.commonService.dismissLoading();
        this.commonService.toast(error);
      }
      );
  }
  editProfile() {
    this.viewCtrl.dismiss();
    this.navCtrl.push(EditProfileComponent);
  }

  changePin() {
    this.viewCtrl.dismiss();
    this.navCtrl.push(ChangePasswordComponent);
  }

  logout() {
    this.commonService.presentLoading();
    this.profileService.logout().subscribe((res: any) => {
      this.viewCtrl.dismiss();
      this.commonService.logout().then(() => {
        this.commonService.dismissLoading();
        this.app.getRootNav().setRoot(LoginComponent);
      }).catch ( () => {
        this.commonService.dismissLoading();        
        this.commonService.toast('Something went wrong');
      });
    });

  }


  switchLogin() {
    this.viewCtrl.dismiss();
    this.switchAccountId = {
      'switchAccountId': this.switchAccount
    }
    this.profileService.switchUser(this.switchAccountId).subscribe((res: any) => {

      this.sql.set('firstname', res.data.firstname);
      this.sql.set('lastname', res.data.lastname);
      this.sql.set('profile_picture', res.data.profile_picture);
      this.sql.set('token', res.data.access_token);
      this.authService.authToken = res.data.access_token;
      this.authService.isLoggedIn = true;
      this.sql.set("dew_points", res.data.dew_points);
      this.sql.set("phone_no", res.data.phone_no);
      this.sql.set("customer_id", res.data._id);
      this.sql.set("switchAccountId", res.data.switchAccountId);
      this.sql.set("currency", res.data.currency);      
      this.sql.set("switchAccountOption", res.data.switchAccountOption);
      this.sql.set("account-type", "customer");
      this.app.getRootNav().setRoot(TabsPage);

    }, (error) => {
      this.commonService.dismissLoading();
      this, this.commonService.toast(error);
    });
  }

}