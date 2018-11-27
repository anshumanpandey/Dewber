import { Component, Input, ViewChild, NgZone } from '@angular/core';
import { NavController, App, PopoverController, ModalController, AlertController, Navbar } from 'ionic-angular';

import { ProfilePage } from './../profile/profile.component';

import { HeaderService } from './header.service';
import { Sql } from './../../providers/sql';

import { MyLoyaltyComponent } from '../my-loyalty/my-loyalty.component';
import { BarcodeScanner } from '@ionic-native/barcode-scanner';
import { CommonService } from '../../providers/common';
import { CustomerDetailsService } from '../business-user/customer-details/customer-details.service';
import { RegisterService } from '../register/register.service';
import { DewPointDetailsService } from '../my-loyalty/dew-point/dew-point-details/dew-point-details.service';
import { DewPointsDetailsComponent } from '../my-loyalty/dew-point/dew-point-details/dew-point-details.component';

@Component({
  selector: 'dewber-header',
  templateUrl: 'header.tmpl.html',
})

export class HeaderComponent {
  header_data: any;
  dew_points: any;
  qr_button: boolean = true;
  search_button: boolean = true;
  back_button: boolean = false;
  searchOption: boolean = false;
  startScan: boolean = false;
  scannedData: string = null;
  subscription2: any;
  toggleBadge: boolean;
  qrbranch: string;

  @ViewChild('navBar') navBar: Navbar;

  constructor(public navCtrl: NavController,
    public modalCtrl: ModalController,
    public sql: Sql,
    public popOverCtrl: PopoverController,
    public app: App,
    private ngZone: NgZone,
    public headerService: HeaderService,
    public alertCtrl: AlertController,
    public commonService: CommonService,
    public barcodeScanner: BarcodeScanner,
    public registerService: RegisterService,
    public customerDetailsService: CustomerDetailsService,
    private dewPointsDetailsService: DewPointDetailsService
  ) {
    this.sql.get('dew_points').then((data) => {
      if (data != undefined && data != null) {
        this.dew_points = parseInt(data);
      }
    });
    this.subscription2 = this.registerService.updateCustomerHeader().subscribe((points) => {
      console.log("customer header recieved", points);
      console.log("customer toggle recieved", this.toggleBadge);
      this.ngZone.run( () => {
         this.toggleBadge = points.flag;
         this.dew_points = points.points;  
      }) 
    });
  }

  @Input()
  set header (header_data: any) {
    this.header_data = header_data;
  }

  @Input()
  set branch (branch: any) {
    this.qrbranch = branch;
  }

  get branch () {
    return this.qrbranch;
  }

  @Input()
  set qr (qr_button: any) {
    this.qr_button = qr_button;
  }

  get qr () {
    return this.qr_button;
  }

  @Input()
  set search (search_button: any) {
    this.search_button = search_button;
  }

  get search () {
    return this.search_button;
  }

  @Input()
  set back (back_button: any) {
    this.back_button = back_button;
  }

  get back () {
    return this.search_button;
  }

  profile () {
    let popover = this.popOverCtrl.create(ProfilePage);
    popover.present();
  }

  qrScanner () {
    this.sql.get("customer_id").then(result => {
      let customerId = result;

      this.startScan = true;
      this.barcodeScanner.scan({ resultDisplayDuration: 0 }).then((barcoadData: Object) => {
        this.scannedData = barcoadData["text"];
        if (this.scannedData.length) {
          let type = this.extractData(this.scannedData);
          if (type.requestType.toLowerCase() == "dewpoint") {
            if (this.qrbranch !== null && typeof this.qrbranch !== "undefined" && this.qrbranch !== '') {
              let request = { rewardId: type.campaingId, branchId: this.qrbranch };
              this.dewPointsDetailsService.redeemDewPoints(request).subscribe((res: any) => {
                if (res.error == false) {
                  this.commonService.toast(res.message);
                } else {
                  this.commonService.toast(res.message);
                }
              });
            } else {
              this.commonService.toast('Please select business branch');
              this.navCtrl.push(MyLoyaltyComponent);
            }
          } else if (type.requestType.toLowerCase() == "dewreward") {
            //  API Is Remaining
            let request = { rewardId: type.campaingId, branchId: customerId };
            this.headerService.redeemDewRewards(request).subscribe((res: any) => {
              if (res.error == false) {
                this.commonService.toast(res.message);
              } else {
                this.commonService.toast(res.message);
              }
            });
          } else {

          }
        }
      }).catch(err => {
        this.commonService.toast(err);
      });
    });

  }

  extractData (scanData) {
    let res = scanData.split("&");
    let type = res[3];
    let id = res[1];

    let campaingId = id.split(":")[1];
    let requestType = type.split(":")[1];
    return { requestType: requestType, campaingId: campaingId };
  }

  searchClicked () {
    this.searchOption = !this.searchOption;
    this.headerService.searchObservable.next(this.searchOption);
  }

  goToMyLoyalty () {
    this.registerService.updateCustomerHeaderObservable.next({
      points: this.dew_points,
      flag: false
    });
    let view = this.navCtrl.getActive();
    if (!((view.instance instanceof MyLoyaltyComponent) || (view.instance instanceof DewPointsDetailsComponent))) {
      this.navCtrl.push(MyLoyaltyComponent);
    }
  }

  ionViewWillLeave () {
    this.subscription2.unsubscribe();
  }
}   