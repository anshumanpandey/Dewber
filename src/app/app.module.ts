import { NgModule, ErrorHandler } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { StatusBar } from '@ionic-native/status-bar';
import { SplashScreen } from '@ionic-native/splash-screen';
import { FileTransfer } from '@ionic-native/file-transfer';

import { MyHttpInterceptor } from './http.interceptor';

import { File } from '@ionic-native/file';
import { FilePath } from '@ionic-native/file-path';
import { Camera } from '@ionic-native/camera';
import { Geolocation } from '@ionic-native/geolocation';

import { MyApp } from './app.component';
import { HeaderComponent } from './../pages/header/header.component';
import { LoginComponent } from '../pages/login/login.component';
import { TabsPage } from '../pages/tabs/tabs';
import { RegisterPage } from '../pages/register/register.component';
import { OutletDetailsComponent } from '../pages/outlet-details/outlet-details.component';
import { NearByComponent } from '../pages/nearby/nearby.component';
import { CheckInComponent } from '../pages/checkin/checkin.component';
import { DewPointComponent } from '../pages/outlet-details/dew-point/dew-point.component';
import { DewRewardComponent } from '../pages/outlet-details/dew-reward/dew-reward.component';
import { InfoMapComponent } from '../pages/outlet-details/info-map/info-map.component';
import { PromotionsComponent } from '../pages/promotions/promotions.component';
import { ProfilePage } from '../pages/profile/profile.component';
import { MyLoyaltyComponent } from '../pages/my-loyalty/my-loyalty.component';
import { MyLoyaltyDewPointComponent } from '../pages/my-loyalty/dew-point/dew-point.component';
import { MyLoyaltyDewRewardComponent } from '../pages/my-loyalty/dew-reward/dew-reward.component';
import { CompanyPopUpComponent } from '../pages/company-popup/company-popup.component';
import { PromotionDetailsComponent } from '../pages/promotion-details/promotion-details.component';
import { DewRewardDetailsComponent } from '../pages/my-loyalty/dew-reward/dew-reward-details/dew-reward-details.component';
import { DewPointsDetailsComponent } from '../pages/my-loyalty/dew-point/dew-point-details/dew-point-details.component';
import { RegularsComponent } from '../pages/regulars/regulars.component';
import { ChangePinComponent } from '../pages/change-pin/change-pin.component';
import { NotificationSettingComponent } from '../pages/notification-setting/notification-setting.component';
import { SetPinComponent } from '../pages/set-pin/set-pin.component';
import { PinComponent } from '../pages/pin/pin.component';
import { BusinessHeaderComponent } from '../pages/business-user/header/header.component';
import { QRCodeComponent } from '../pages/qr-code/qr-code.component';

import { SendFeedBackComponent } from '../pages/feedback/feedback.component';
import { BusinessUserProfilePage } from '../pages/business-user/profile/profile.component';

import { CustomerDetailsComponent } from '../pages/business-user/customer-details/customer-details.component';
import { PointsPopUpPage } from './../pages/business-user/points-popup/points-popup.component';
import { UserRegistrationComponent } from './../pages/business-user/user-registration/user-registration.component';
import { LocationDetailsComponent } from '../pages/location-details/location-details.component';
import { EditProfileComponent } from '../pages/business-user/edit-profile/edit-profile.component';
import { ChangePasswordComponent } from './../pages/business-user/change-password/change-password.component';
import { LocationCheckComponent } from '../pages/location-check/location-check.component';
import { ForgotPasswordComponent } from '../pages/forgot-password/forgot-password.component';
import { SetPinComponentBusinessUser } from '../pages/business-user/set-pin/set-pin.component'
import { ReSetPinComponent } from '../pages/reset-pin/reset-pin.component';


// Services
import { RegisterService } from '../pages/register/register.service';
import { LoginService } from '../pages/login/login.service';
import { AuthService } from '../pages/login/auth.service';
import { NearByService } from '../pages/nearby/nearby.service';
import { DewRewardService } from '../pages/outlet-details/dew-reward/dew-reward.service';
import { DewPointService } from '../pages/outlet-details/dew-point/dew-point.service';
import { PromotionsService } from '../pages/promotions/promotions.service';
import { ProfileService } from '../pages/profile/profile.service';
import { CheckinService } from '../pages/checkin/checkin.service';
import { CompanyPopUpService } from '../pages/company-popup/company-popup.service';
import { PromotionDetailsService } from '../pages/promotion-details/promotion-details.service';
import { DewRewardDetailsService } from '../pages/my-loyalty/dew-reward/dew-reward-details/dew-reward-details.service';
import { DewPointDetailsService } from '../pages/my-loyalty/dew-point/dew-point-details/dew-point-details.service';
import { ChangePinService } from '../pages/change-pin/change-pin.service';
import { MyLoyaltyDewPointService } from './../pages/my-loyalty/dew-point/dew-point.service';
import { MyLoyaltyDewRewardService } from '../pages/my-loyalty/dew-reward/dew-reward.service';
import { RegularsService } from '../pages/regulars/regulars.service';
import { NotificationSettingService } from '../pages/notification-setting/notification-setting.service';
import { SetPinService } from '../pages/set-pin/set-pin.service';
import { BusinessUserProfileService } from '../pages/business-user/profile/profile.service';
import { CustomerDetailsService } from './../pages/business-user/customer-details/customer-details.service';
import { PointsPopUpService } from './../pages/business-user/points-popup/points-popup.service';
import { UserRegistrationService } from './../pages/business-user/user-registration/user-registration.service';
import { ChangePasswordService } from './../pages/business-user/change-password/change-password.service';
import { EditProfileService } from '../pages/business-user/edit-profile/edit-profile.service';
import { ForgotPasswordService } from '../pages/forgot-password/forgot-password.service';
import { HeaderService } from './../pages/header/header.service';
import { BusinessHeaderService } from '../pages/business-user/header/header.service';
import { SetPinServiceBusinessUser } from '../pages/business-user/set-pin/set-pin.service';
import { ReSetPinService } from '../pages/reset-pin/reset-pin.service';
import { PinService } from '../pages/pin/pin.service';

import { Sql } from '../providers/sql';
import { CommonService } from '../providers/common';

// 3rd party integrations
//import { RatingModule } from "ngx-rating";
import { NgxQRCodeModule } from 'ngx-qrcode2';
import { BarcodeScanner } from '@ionic-native/barcode-scanner';
import { Device } from '@ionic-native/device';
import { OneSignal } from '@ionic-native/onesignal';
import { MyCustomersComponent } from '../pages/business-user/my-customers/my-customers.component';
import { MyCustomerService } from '../pages/business-user/my-customers/my-customer.service';
import { SendFeedBackService } from '../pages/feedback/feedback.service';
import { PipesModule } from './../providers/numeral/pipe-module';


import { RichTextComponent } from '../component/rich-text.component';
import { QueryEncoder } from "@angular/http";
import { BadgeProvider } from '../pages/login/badge.service';

@NgModule({
  declarations: [
    MyApp,
    LoginComponent,
    HeaderComponent,
    BusinessHeaderComponent,
    TabsPage,
    RegisterPage,
    ChangePinComponent,
    OutletDetailsComponent,
    NearByComponent,
    CheckInComponent,
    DewPointComponent,
    DewRewardComponent,
    InfoMapComponent,
    PromotionsComponent,
    QRCodeComponent,
    ProfilePage,
    RegularsComponent,
    MyLoyaltyComponent,
    MyLoyaltyDewPointComponent,
    MyLoyaltyDewRewardComponent,
    CompanyPopUpComponent,
    PromotionDetailsComponent,
    DewRewardDetailsComponent,
    DewPointsDetailsComponent,
    NotificationSettingComponent,
    SetPinComponent,
    MyCustomersComponent,
    BusinessUserProfilePage,
    SendFeedBackComponent,
    LocationDetailsComponent,
    RichTextComponent,
    CustomerDetailsComponent,
    PointsPopUpPage,
    UserRegistrationComponent,
    ChangePasswordComponent,
    QRCodeComponent,
    EditProfileComponent,
    LocationCheckComponent,
    ForgotPasswordComponent,
    SetPinComponentBusinessUser,
    ReSetPinComponent,
    PinComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    //RatingModule,
    NgxQRCodeModule,
    PipesModule,
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,
    LoginComponent,
    HeaderComponent,
    BusinessHeaderComponent,
    TabsPage,
    RegisterPage,
    ChangePinComponent,
    OutletDetailsComponent,
    NearByComponent,
    CheckInComponent,
    DewPointComponent,
    DewRewardComponent,
    InfoMapComponent,
    PromotionsComponent,
    QRCodeComponent,
    ProfilePage,
    RegularsComponent,
    MyLoyaltyComponent,
    MyLoyaltyDewPointComponent,
    MyLoyaltyDewRewardComponent,
    CompanyPopUpComponent,
    PromotionDetailsComponent,
    DewRewardDetailsComponent,
    DewPointsDetailsComponent,
    NotificationSettingComponent,
    SetPinComponent,
    MyCustomersComponent,
    BusinessUserProfilePage,
    SendFeedBackComponent,
    LocationDetailsComponent,
    RichTextComponent,
    CustomerDetailsComponent,
    PointsPopUpPage,
    UserRegistrationComponent,
    ChangePasswordComponent,
    QRCodeComponent,
    EditProfileComponent,
    LocationCheckComponent,
    ForgotPasswordComponent,
    SetPinComponent,
    SetPinComponentBusinessUser,
    ReSetPinComponent,
    PinComponent

  ],
  providers: [
    StatusBar,
    SplashScreen,
    File,
    FileTransfer,
    Camera,
    FilePath,
    Geolocation,
    BarcodeScanner,
    Device,
    OneSignal,
    RegisterService,
    LoginService,
    AuthService,
    ChangePinService,
    NearByService,
    Sql,
    QueryEncoder,
    CommonService,
    DewRewardService,
    DewPointService,
    PromotionsService,
    ProfileService,
    CheckinService,
    RegularsService,
    CompanyPopUpService,
    PromotionDetailsService,
    DewRewardDetailsService,
    DewPointDetailsService,
    SetPinService,
    NotificationSettingService,
    MyLoyaltyDewPointService,
    MyLoyaltyDewRewardService,
    MyCustomerService,
    SendFeedBackService,
    CustomerDetailsService,
    PointsPopUpService,
    UserRegistrationService,
    ChangePasswordService,
    EditProfileService,
    ForgotPasswordService,
    HeaderService,
    BusinessHeaderService,
    SetPinServiceBusinessUser,
    BadgeProvider,
    BusinessUserProfileService,
    ReSetPinService,
    PinService,
    { provide: ErrorHandler, useClass: IonicErrorHandler },
    { provide: HTTP_INTERCEPTORS, useClass: MyHttpInterceptor, multi: true }
  ]
})
export class AppModule { }
