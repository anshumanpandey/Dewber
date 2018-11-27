import { FormControl,  FormBuilder } from '@angular/forms';
import { ViewController } from 'ionic-angular';
import { Component } from '@angular/core';
import { SendFeedBackService } from './feedback.service';
import { CommonService } from '../../providers/common';

@Component({
    selector: 'app-send-feedback',
    templateUrl: './feedback.component.html',
})
export class SendFeedBackComponent { 
    private feedback:FormControl;
    constructor(private feedbackService:SendFeedBackService,
        private viewCtrl: ViewController,
        private formBuilder: FormBuilder,
        private commonService:CommonService){
            this.feedback = this.formBuilder.control('');
    }

    postFeedback(){
        let data = {
            feedback: this.feedback.value
        }
        this.feedbackService.postFeedback(data).subscribe((response:any)=>{
            if (response.error == false) {
                this.viewCtrl.dismiss();
                this.commonService.toast(response.message);
            } else {
                this.commonService.toast(response.message);
            }
        }, (error) => {
            
        });
    }

    cancel() {
        this.viewCtrl.dismiss();
    }
}