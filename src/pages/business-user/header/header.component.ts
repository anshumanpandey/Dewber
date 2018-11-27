import { GlobalVariable } from './../../../app/app.component';
import { Component, Input, ChangeDetectorRef, NgZone } from '@angular/core';
import { NavController, App, PopoverController, ModalController, PopoverOptions, Events } from 'ionic-angular';

import { Sql } from '../../../providers/sql';
import { BusinessUserProfilePage } from '../profile/profile.component';
import { EditProfileComponent } from './../edit-profile/edit-profile.component';
import { PointsPopUpPage } from '../points-popup/points-popup.component';
import { Subscription } from 'rxjs';
import { CustomerDetailsService } from '../customer-details/customer-details.service';
import { EditProfileService } from '../edit-profile/edit-profile.service';


@Component({
  selector: 'dewber-business-header',
  templateUrl: 'header.tmpl.html',
})
export class BusinessHeaderComponent {
  header_data: any;
  private dew_points: any;
  qr_button: boolean = true;
  search_button: boolean = true;
  back_button: boolean = false;
  private user_full_name: any = null;
  private first_name: any = null;
  private last_name: any = null;
  public subscription1: Subscription;
  public toggleBadge = false;
  profile_picture:any;
  loadIcon:boolean = false;
  baseURL = GlobalVariable.BASE_URL;
  branchName: string;

  constructor(
    public navCtrl: NavController,
    public sql: Sql,
    public popOverCtrl: PopoverController,
    public modalCtrl: ModalController,
    public app: App,
    private ngZone: NgZone,
    public profileService: EditProfileService,
    public customerDetailsService: CustomerDetailsService,
    private event:Events,
    private detectorRef: ChangeDetectorRef
  ) {

    this.sql.get('dew_points').then((data) => {
      if (data != undefined && data != null) {
        this.dew_points = parseInt(data);
      }
    });

    this.sql.get("businessUserBranchName").then(data => {
      if (data != undefined && data != null) {
        this.branchName = data;
      }
    });
    
    this.event.subscribe('profile:updated', flag => {
      if(flag == true ) {
      this.sql.get('profile_picture').then((data) => {
        this.profile_picture = data;
        this.detectorRef.detectChanges();
      }); 
    }
    });
    
    this.sql.get('profile_picture').then((data) => {
      this.profile_picture = data;
      this.loadIcon = true;
    });
    
    this.sql.get('firstname').then((data) => {

      if (data != undefined && data != null) {
        this.first_name = data;
      }
      this.sql.get('lastname').then((data) => {

        if (data != undefined && data != null) {
          this.last_name = data;
        }
        this.user_full_name = this.first_name + " " + this.last_name;
      });
    });

    this.subscription1 = this.customerDetailsService.updateBusinessHeader().subscribe((points) => {
       console.log("business header recieved", points);
       console.log(this.toggleBadge);
       this.ngZone.run( () => {
          this.toggleBadge = points.flag;
          this.dew_points = points.points;  
       })  
    });
  }

  @Input()
  set header(header_data: any) {
    this.header_data = header_data;
  }

  get header() {
    return this.header_data;
  }

  @Input()
  set back(back_button: any) {
    this.back_button = back_button;
  }

  get back() {
    return this.search_button;
  }

  pointsDetails() {
    this.customerDetailsService.updateBusinessHeaderObservable.next({
      flag: false,
      points: this.dew_points
    });
    this.toggleBadge = false;
    let option: PopoverOptions = {
      "cssClass": 'pointsPopOver'
    }
    let points = this.modalCtrl.create(PointsPopUpPage, {}, option);
    points.present();
  }

  profile() {
    let popover = this.popOverCtrl.create(BusinessUserProfilePage);
    popover.present();
  }

  editProfile() {
    this.navCtrl.push(EditProfileComponent);
  }

  ionViewWillLeave() {
    this.subscription1.unsubscribe();
  }

}   