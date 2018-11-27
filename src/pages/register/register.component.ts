import { GlobalVariable } from './../../app/app.component';
import { Component } from '@angular/core';
import { NavController, ActionSheetController, Platform, App, NavParams } from 'ionic-angular';
import { FormGroup, FormControl, Validators, ValidationErrors } from '@angular/forms';
import { FileTransferObject, FileTransfer } from '@ionic-native/file-transfer';

import { CountryCodes, Register } from './register.class';

import { RegisterService } from './register.service';
import { CommonService } from '../../providers/common';

import { File } from '@ionic-native/file';
import { FilePath } from '@ionic-native/file-path';
import { Camera } from '@ionic-native/camera';
import { TabsPage } from '../tabs/tabs';
import { SetPinComponent } from '../set-pin/set-pin.component';
import { PinComponent } from '../pin/pin.component';
import { Sql } from '../../providers/sql';
import { AuthService } from '../login/auth.service';
import { OneSignal } from '@ionic-native/onesignal';
declare var cordova: any;

@Component({
  selector: 'page-register',
  templateUrl: 'register.component.html'
})

export class RegisterPage {
  base_url = GlobalVariable.BASE_URL;
  registerForm: FormGroup;
  registerData: Register;
  countryList: Array<CountryCodes> = [];
  lastImage: string = null;
  header: string = null;
  backButton: boolean = false;
  profile_button_text: string = null;
  userDetails: Register;
  countryDialCode: string;

  private referralCodeData: boolean = true;
  private referralCode: boolean = true;
  private targetPath: any = '';
  private profileDetails: any;
  private updateScreen = false;

  constructor(
    private navCtrl: NavController,
    private platform: Platform,
    private app: App,
    private authService: AuthService,
    private commonService: CommonService,
    private _registerService: RegisterService,
    private actionSheetCtrl: ActionSheetController,
    private oneSignal: OneSignal,
    private camera: Camera,
    private transfer: FileTransfer,
    private filePath: FilePath,
    private file: File,
    private navParams: NavParams,
    private sql: Sql) {

    // Check referralCode field
    this.referralCodeData = this.navParams.get('referralCode');

    this.registerForm = new FormGroup({
      'firstname': new FormControl(null, Validators.required),
      'lastname': new FormControl(null, Validators.required),
      'email': new FormControl(null, [Validators.required, Validators.email]),
      'phone_no': new FormControl(null, [Validators.required, Validators.pattern('[0-9]{1}[0-9]{8,9}'), Validators.minLength(9), Validators.maxLength(10)]),
      'country': new FormControl(null, Validators.required),
      'dialCode': new FormControl(null, Validators.required),
      'referral_code': new FormControl(null)
    });

    this.registerForm.get('country').valueChanges.subscribe((data) => {
      this.countryList.forEach((element) => {
        if (element._id == data) {
          if (this.registerForm.controls['dialCode'].value != element._id) {
            this.registerForm.controls['dialCode'].setValue(element._id);
          }
        }
      });
    });

    this.registerForm.get('dialCode').valueChanges.subscribe((data) => {
      this.countryList.forEach((element) => {
        if (element._id == data) {
          if (this.registerForm.controls['country'].value != element._id) {
            this.registerForm.controls['country'].setValue(element._id);
          }
        }
      });
    });

    if (this.referralCodeData == undefined) {
      this.referralCode = true;
      this.header = "Register";
      this.profile_button_text = "Create an Account";
    } else {
      this.referralCode = false;

      this.header = "Update Profile";
      this.profile_button_text = "Update";
      this.backButton = true;
      this.commonService.presentLoading();
      this._registerService.getProfileDetails().subscribe((res: any) => {
        this.profileDetails = res.data;
        this.updateScreen = true;
        this.registerForm.patchValue(this.profileDetails);
        this.registerForm.get('dialCode').setValue(this.profileDetails.country, {
          onlySelf: false, emitEvent: true
        });
        this.commonService.dismissLoading();
      }, (error) => {
        this.commonService.dismissLoading();
      });
    }

  }

  ionViewWillEnter () {
    if (this.platform.is('cordova')) {
      this.oneSignal.startInit('d5f92180-25cf-44e8-b5ed-7dfaea228513', '421806810556');
      this.oneSignal.getIds().then((data) => {
        this.sql.set('oneSignalId', data.userId);
      });

      this.oneSignal.endInit();
    }

    this._registerService.getCountryCodes().subscribe((list) => {
      this.countryList = list.data;
    }, (err) => {
      this.commonService.toast("Something went wrong");
    });
  }

  // File upload
  presentActionSheet () {
    let actionSheet = this.actionSheetCtrl.create({
      title: 'Upload a photo using',
      buttons: [
        {
          text: 'Gallery',
          handler: () => {
            this.loadFromGallery();
          }
        }, {
          text: 'Camera',
          handler: () => {
            this.photo();
          }
        }, {
          text: 'Cancel',
          role: 'cancel',
          handler: () => {
          }
        }
      ]
    });
    actionSheet.present();
  }

  loadFromGallery () {
    this.takePicture(this.camera.PictureSourceType.PHOTOLIBRARY);
  }

  photo () {
    this.takePicture(this.camera.PictureSourceType.CAMERA);
  }

  takePicture (sourceType) {
    // Create options for the Camera Dialog
    var options = {
      quality: 100,
      sourceType: sourceType,
      saveToPhotoAlbum: false,
      correctOrientation: true
    };

    // Get the data of an image
    this.camera.getPicture(options).then((imagePath) => {
      // Special handling for Android library
      if (this.platform.is('android') && sourceType === this.camera.PictureSourceType.PHOTOLIBRARY) {
        this.filePath.resolveNativePath(imagePath)
          .then(filePath => {
            let correctPath = filePath.substr(0, filePath.lastIndexOf('/') + 1);
            let currentName = imagePath.substring(imagePath.lastIndexOf('/') + 1, imagePath.lastIndexOf('?'));
            this.copyFileToLocalDir(correctPath, currentName, this.createFileName());
          });
      } else {
        var currentName = imagePath.substr(imagePath.lastIndexOf('/') + 1);
        var correctPath = imagePath.substr(0, imagePath.lastIndexOf('/') + 1);
        this.copyFileToLocalDir(correctPath, currentName, this.createFileName());
      }
    }, (err) => {
      this.commonService.toast('Error while selecting image.');
    });
  }

  // Create a new name for the image
  private createFileName () {
    var d = new Date(),
      n = d.getTime(),
      newFileName = n + ".jpeg";
    return newFileName;
  }

  // Copy the image to a local folder
  private copyFileToLocalDir (namePath, currentName, newFileName) {
    this.file.copyFile(namePath, currentName, cordova.file.dataDirectory, newFileName).then(success => {
      this.lastImage = newFileName;
    }, error => {
      this.commonService.toast('Error while storing file.');
    });
  }

  // Always get the accurate path to your apps folder
  pathForImage (img) {
    if (img == null) {
      return '';
    }
    else {
      let itemSrc = cordova.file.dataDirectory + img;

      if (this.platform.is('ios')) {
        itemSrc = itemSrc.replace(/^file:\/\//, '');
      }

      return itemSrc;
    }
  }

  getCountryDialCode (data) {
    this.countryList.forEach((element) => {
      if (element._id == data) {
        this.countryDialCode = element.dial_code;
      }
    });
  }

  uploadData () {
    this.sql.get('oneSignalId').then((data) => {
      this.registerData = this.registerForm.value;
      this.getCountryDialCode(this.registerForm.controls['dialCode'].value);
      this.registerData.full_phone = this.countryDialCode + this.registerForm.controls['phone_no'].value;
      this.userDetails = Object.assign({}, this.registerData);
      this.userDetails.onesignalid = data;

      if (this.registerForm.valid) {
        if (this.referralCode) {
          this.customerRegister();
        } else {
          this.updateCustomer();
        }
      } else {
        if (this.getFormValidationErrors('phone_no')) {
          this.commonService.toast("Please enter valid mobile number (9-10 digits allowed).");
        } else {
          this.commonService.toast("Please enter valid data in all the field.");
        }
      }
    });
  }

  customerRegister () {
    let url = GlobalVariable.BASE_API_URL + "customer/signup";
    if (this.lastImage != null) {
      // File for Upload
      this.targetPath = this.pathForImage(this.lastImage);

      // File name only
      let filename = this.lastImage;

      let options = {
        fileKey: "profile_picture",
        fileName: filename,
        params: this.userDetails
      };
      const fileTransfer: FileTransferObject = this.transfer.create();


      // Use the FileTransfer to upload the image
      this.commonService.presentLoading();
      fileTransfer.upload(this.targetPath, encodeURI(url), options).then((response: any) => {
        const customerData = JSON.parse(response.response);
        this.postCustomerCreate(customerData);
      }, (error) => {
        this.commonService.dismissLoading();
        this.commonService.toast('Something went wrong');
      });
    } else {
      this.commonService.presentLoading();
      this._registerService.register(this.userDetails).subscribe((response: any) => {
        this.postCustomerCreate(response);
      });
    }
  }

  postCustomerCreate (response) {
    if (response.error == false) {
      this.commonService.dismissLoading();
      this.navCtrl.pop();
      if (response.data.is_first_time == false) {
        if (response.data.force_change_pin == false) {
          this.app.getRootNav().setRoot(TabsPage);
        } else {
          this.navCtrl.push(SetPinComponent, { 'otp': response.data.mobile_otp });
        }
      } else {
        this.navCtrl.push(SetPinComponent, { 'otp': response.data.mobile_otp });
      }
    } else {
      this.commonService.dismissLoading();
      this.commonService.toast(response.message);
    }

  }

  updateCustomer () {
    let url = GlobalVariable.BASE_API_URL + "customer/update-profile";
    if (this.lastImage != null) {
      // File for Upload
      this.targetPath = this.pathForImage(this.lastImage);

      // File name only
      let filename = this.lastImage;

      let options = {
        fileKey: "profile_picture",
        fileName: filename,
        params: this.userDetails,
        headers: {
          "X-Authorization": this.authService.getToken()
        }
      };
      const fileTransfer: FileTransferObject = this.transfer.create();

      // Use the FileTransfer to upload the image
      this.commonService.presentLoading();

      fileTransfer.upload(this.targetPath, encodeURI(url), options).then((response: any) => {
        const customerData = JSON.parse(response.response);
        this.postCustomerUpdate(customerData);
      }, (error) => {
        this.commonService.dismissLoading();
        this.commonService.toast('Something went wrong');
      });
    } else {
      this._registerService.updateProfile(this.userDetails).subscribe((response: any) => {
        this.postCustomerUpdate(response);
      }, (error) => {
        this.commonService.dismissLoading();
        this.commonService.toast('Something went wrong');
      })
    };
  }

  postCustomerUpdate (response) {
    if (response.error == false) {
      this.navCtrl.pop();
      if (response.data.otp_sent) {
        this.navCtrl.push(PinComponent, {
          data: {
            'otp': response.data.mobile_otp,
            'profile': this.userDetails
          }
        });
      } else {
        this.commonService.toast(response.message);
        this.sql.set("firstname", response.data.firstname);
        this.sql.set("lastname", response.data.lastname);
        this.sql.set("profile_picture", response.data.profile_picture);
      }
      this.commonService.dismissLoading();
    } else {
      this.commonService.dismissLoading();
      this.commonService.toast(response.message);
    }
  }

  getFormValidationErrors (key) {
    const controlErrors: ValidationErrors = this.registerForm.get(key).errors;
    if (controlErrors != null) {
      return true;
    }
  }

}
