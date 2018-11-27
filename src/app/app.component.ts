import { RegisterService } from './../pages/register/register.service';
import { EditProfileService } from './../pages/business-user/edit-profile/edit-profile.service';
import { AuthService } from './../pages/login/auth.service';

import { Component } from '@angular/core';
import { Platform } from 'ionic-angular';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { OneSignal } from '@ionic-native/onesignal';

import { LoginComponent } from '../pages/login/login.component';
import { TabsPage } from '../pages/tabs/tabs';

import { Sql } from '../providers/sql';
import { MyCustomersComponent } from '../pages/business-user/my-customers/my-customers.component';
import { CustomerDetailsService } from '../pages/business-user/customer-details/customer-details.service';

@Component({
  templateUrl: 'app.html'
})
export class MyApp {
  rootPage: any;

  constructor(platform: Platform,
    statusBar: StatusBar,
    splashScreen: SplashScreen,
    private authService: AuthService,
    private sql: Sql,
    private profileService: EditProfileService,
    private registerService: RegisterService,
    private customerDetailsService: CustomerDetailsService,
    private oneSignal: OneSignal) {

    platform.ready().then(() => {
      if (platform.is('cordova')) {
        this.oneSignal.startInit('d5f92180-25cf-44e8-b5ed-7dfaea228513', '421806810556');
        this.oneSignal.getIds().then((data) => {
          this.sql.set('oneSignalId', data.userId);
        });
        this.oneSignal.inFocusDisplaying(this.oneSignal.OSInFocusDisplayOption.None);

        this.oneSignal.handleNotificationReceived().subscribe((data: any) => {
          console.log("notificaiton recieved");
          let parseData = JSON.parse(data.payload.body);
          console.log("body recieved", parseData);
          this.sql.get('customer_id').then((id) => {
            if (parseData.type === "customer") {
              if (id == parseData.id) {
                console.log("customer recieved", parseData);
                let dew_points = {
                  points: parseData.dew_points,
                  flag: parseData.showGreenDot
                }
                this.sql.set('dew_points', parseData.dew_points);
                this.registerService.updateCustomerHeaderObservable.next(dew_points);
              }
            } else if (parseData.type === "business_user") {
              if (id == parseData.id) {
                
                let dew_points = {
                  points: parseData.dew_points,
                  flag: parseData.showGreenDot
                }
                console.log("business recieved", parseData);
                this.sql.set('dew_points', parseData.dew_points);
                this.customerDetailsService.updateBusinessHeaderObservable.next(dew_points);
                
              }
            }
          })
          // do something when notification is received
        });

        this.oneSignal.handleNotificationOpened().subscribe(() => {
          // do something when a notification is opened
        });

        this.oneSignal.endInit();
      }
      this.sql.get('account-type').then(res => {
        if (res === "customer") {
          this.sql.get("isLoggedIn").then(res => {
            if (res == "true") {
              this.sql.get('token').then((res) => {
                this.authService.authToken = res;
                this.authService.isLoggedIn = true;
                this.rootPage = TabsPage;
              });
            } else {
              this.oneSignal.getIds().then((data) => {
                this.sql.set('oneSignalId', data.userId);
                this.rootPage = LoginComponent;
              });
            }
          })
        } else {
          this.sql.get('isLoggedIn').then(res => {
            if (res == "true") {
              this.sql.get('token').then((res) => {
                this.authService.authToken = res;
                this.authService.isLoggedIn = true;
                this.rootPage = MyCustomersComponent;
              });
            } else {
              this.rootPage = LoginComponent;
            }
          })
        }
      })
        .catch(error => {
          statusBar.styleDefault();
          splashScreen.hide();
          this.rootPage = LoginComponent;
        })
    });
  }
}


export const GlobalVariable = Object.freeze({
 // BASE_URL: 'https://dewber.com/',
  //BASE_API_URL: 'https://dewber.com/api/',
  // ENV: 'production'
   //BASE_URL: 'http://46.101.13.84/',
   //BASE_API_URL: 'http://46.101.13.84/api/',
   BASE_URL: 'https://dewber-nodejs.herokuapp.com/',
   BASE_API_URL: 'https://dewber-nodejs.herokuapp.com/api/',
    ENV: 'development'
});



