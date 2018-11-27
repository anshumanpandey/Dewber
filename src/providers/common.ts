import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { LoadingController, ToastController } from 'ionic-angular';
import { WebHttpUrlEncodingCodec } from '../providers/custom-coder';
import { Sql } from './sql';
import { AuthService } from '../pages/login/auth.service';
import { GlobalVariable } from '../app/app.component';

@Injectable()
export class CommonService {
  loading: any;
  fd: any;
  loader = false;

  constructor(private loadingCtrl: LoadingController,
    private authService: AuthService,
    private sql: Sql,
    private toastCtrl: ToastController) {
  }

  presentLoading () {
    if (!this.loader) {
      this.loading = this.loadingCtrl.create({
        content: 'Please wait...'
      });


      this.loading.onDidDismiss(() => {
        this.loader = false;
      });
      this.loader = true;
      this.loading.present();
    }
  }

  dismissLoading () {
    if (this.loader === true) {
      this.loading.dismiss();
    }
  }

  toast (msg: string) {
    let toast = this.toastCtrl.create({
      message: msg,
      duration: 3000,
      position: 'bottom'
    });

    toast.present();
  }

  convertFormData (data) {
    this.fd = new FormData();
    for (var key in data) {
      this.fd.append(key, data[key]);
    }
    return this.fd;
  }

  convertURLEncData (data) {
    this.fd = new HttpParams({ encoder: new WebHttpUrlEncodingCodec() });
    for (var key in data) {
      this.fd = this.fd.set(key, data[key]);
    }
    return this.fd;
  }

  convertEncodeData (data) {
    this.fd = new URLSearchParams();
    for (var key in data) {
      this.fd = this.fd.set(key, data[key]);
    }
    return this.fd;
  }

  getenv() {
    if (GlobalVariable.ENV === 'development') {
       return true;
    } else {
      return false;
    }
  }

  setCustomer(res) {
    this.authService.authToken = res.data.access_token;
    this.authService.isLoggedIn = true;
    this.sql.set('token', res.data.access_token);
    this.sql.set('isLoggedIn', 'true');
    this.sql.set('customer_id', res.data._id);
    this.sql.set('dew_points', res.data.dew_points);
    this.sql.set('switchAccountId', res.data.switchAccountId);
    this.sql.set('profile_picture', res.data.profile_picture);
    this.sql.set('switchAccountOption', res.data.switchAccountOption);
    this.sql.set('firstname', res.data.firstname);
    this.sql.set('lastname', res.data.lastname);
    this.sql.set('phone_no', res.data.phone_no);
    this.sql.set('currency', res.data.currency);
    this.sql.set('account-type', 'customer');
  }

  setBusinessUser(res) {
    this.authService.authToken = res.data.access_token;
    this.authService.isLoggedIn = true;
    this.sql.set('token', res.data.access_token);
    this.sql.set('isLoggedIn', 'true');
    this.sql.set('dew_points', res.data.dew_points);
    this.sql.set('firstname', res.data.firstname);
    this.sql.set('lastname', res.data.lastname);
    this.sql.set('profile_picture', res.data.profile_picture);
    this.sql.set('switchAccountId', res.data.switchAccountId);
    this.sql.set('switchAccountOption', res.data.switchAccountOption);
    this.sql.set('customer_id', res.data._id);
    this.sql.set('countrycode', res.data.code);
    this.sql.set('currency', res.data.currency);
    this.sql.set('isDefaultAccount', res.data.isDefaultAccount);
    this.sql.set('businessUserBranchName', res.data.branch_name);
    this.sql.set('account-type', 'business');
  }

  logout () {
    return new Promise((resolve, reject) => {
      this.sql.remove('token');
      this.sql.remove('isLoggedIn');
      this.sql.remove('customer_id');
      this.sql.remove('dew_points');
      this.sql.remove('switchAccountId');
      this.sql.remove('switchAccountOption');
      this.sql.remove('firstname');
      this.sql.remove('lastname');
      this.sql.remove('phone_no');
      this.sql.remove('count');
      this.sql.remove('profile_picture');
      this.sql.remove('countrycode');
      this.sql.remove('isDefaultAccount');
      this.sql.remove('account-type');
      this.authService.authToken = null;
      this.authService.isLoggedIn = false;
      resolve();
    });

  }
}