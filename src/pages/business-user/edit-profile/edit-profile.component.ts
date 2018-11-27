import { GlobalVariable } from './../../../app/app.component';
import { Component } from '@angular/core';
import { NavController, ActionSheetController, Platform, ViewController, Events } from 'ionic-angular';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { File } from '@ionic-native/file';
import { FilePath } from '@ionic-native/file-path';
import { Camera } from '@ionic-native/camera';
import { FileTransferObject, FileTransfer } from '@ionic-native/file-transfer';
import { CommonService } from '../../../providers/common';
import { Sql } from '../../../providers/sql';
import { EditProfileService } from './edit-profile.service';
import { EditProfile, CountryCodes } from './edit-profile.classes';
import { RegisterService } from '../../register/register.service';
import { AuthService } from '../../login/auth.service';

declare var cordova: any;

@Component({
    selector: 'app-edit-profile',
    templateUrl: './edit-profile.component.html',
})
export class EditProfileComponent {
    editProfileForm: FormGroup;
    editProfileData: EditProfile;
    countryList: Array<CountryCodes> = [];
    lastImage: string = null;
    elInput: any;
    header: string = null;
    backButton: boolean = false;
    profile_button_text: string = null;
    userDetails: EditProfile;
    firstname: string;
    lastname: string;
    loadTemplate: boolean = false;
    tempCountryCode: any;
    baseURL = GlobalVariable.BASE_URL;
    countryDialCode: any = '';

    private targetPath: any = '';
    private profileDetails: any;
    private countryPlcaeHolder: any;

    constructor(
        private navCtrl: NavController,
        private platform: Platform,
        private authService: AuthService,
        private commonService: CommonService,
        private actionSheetCtrl: ActionSheetController,
        private camera: Camera,
        private edtProfileService: EditProfileService,
        private transfer: FileTransfer,
        private filePath: FilePath,
        private file: File,
        private sql: Sql,
        private viewCtrl: ViewController,
        private _registerService: RegisterService,
        private events: Events) {

        this.profile_button_text = "Update";
        this.header = "Edit Profile";

        
        //get profile details
        this.edtProfileService.getProfileDetails().subscribe((res: any) => {
            this.profileDetails = res.data;
            this.editProfileForm = new FormGroup({
                'firstname': new FormControl(this.profileDetails.firstname, Validators.required),
                'lastname': new FormControl(this.profileDetails.lastname, Validators.required),
                'email': new FormControl(this.profileDetails.email, [Validators.required, Validators.email]),
                'phone_no': new FormControl(this.profileDetails.phone_no, [Validators.pattern('[1-9]{1}[0-9]{9}'),Validators.required,Validators.minLength(10), Validators.maxLength(10)]),
                'dialCode': new FormControl(this.profileDetails.country)
            });

            this.loadTemplate = true;

            this._registerService.getCountryCodes().subscribe((list) => {
                this.countryList = list.data;
                this.countryList.forEach((element) => {
                    if (element._id == this.profileDetails.country) {
                        this.countryDialCode = element.dial_code;
                        this.editProfileForm.controls['dialCode'].setValue(element._id);
                    }
                });
            }, (err) => {
                this.commonService.toast("Something went wrong");
            });


            this.editProfileForm.get('dialCode').valueChanges.subscribe((data) => {
                this.countryList.forEach((element) => {
                    if (element._id == data) {
                        this.countryDialCode = element.dial_code;
                    }
                });
            });
        });
    }

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
            // this.uploadImage();
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

    getCountryDialCode(data) {
      this.countryList.forEach((element) => {
        if (element._id == data) {
            this.countryDialCode = element.dial_code;
        }
      });
    }

    uploadData () {
        //country code and number
        this.editProfileData = this.editProfileForm.value;
        this.getCountryDialCode(this.editProfileForm.controls['phone_no'].value);
        this.editProfileData.full_phone =  this.countryDialCode + this.editProfileForm.controls['phone_no'].value;
        this.userDetails = Object.assign({}, this.editProfileData);

        let url = GlobalVariable.BASE_API_URL + "business-user/update-profile";

        if (this.editProfileForm.valid) {

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
                    response.response = JSON.parse(response.response);
                    if (response.response.error == false) {
                        this.commonService.dismissLoading();
                        this.commonService.toast(response.response.message);
                        this.sql.set("firstname", response.response.data.firstname);
                        this.sql.set("lastname", response.response.data.lastname);
                        this.sql.set("profile_picture", response.response.data.profile_picture);
                        this.events.publish('profile:updated', true);
                        this.navCtrl.pop();
                    }
                }, (error) => {
                    this.commonService.dismissLoading();
                    this.commonService.toast('Something went wrong');
                });
            } else {
                this.edtProfileService.editProfile(this.userDetails).subscribe((res: any) => {
                    this.commonService.toast(res.message);
                    this.sql.set("firstname", res.data.firstname);
                    this.sql.set("lastname", res.data.lastname);
                    this.sql.set("profile_picture", res.data.profile_picture);
                    this.navCtrl.pop();
                }, (error) => {
                    this.commonService.dismissLoading();
                    this, this.commonService.toast(error);
                });

            }
        } else {
            this.commonService.toast("Please fill the form");
        }
    }
}
