import { Component, ViewChild, ElementRef } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';
 
declare var google;
 
@Component({
  templateUrl: 'location-details.tmpl.html'
})
export class LocationDetailsComponent {
  location:any;
  name:any;
  @ViewChild('map') mapElement: ElementRef;
  map: any;
  latLng:any;
 
  constructor(public navCtrl: NavController, private navParams:NavParams) {
    this.location = this.navParams.get('location');
    this.name = this.navParams.get('name');
  }
 
  ionViewDidLoad(){
    this.loadMap();
  }
 
  loadMap(){
    this.latLng = new google.maps.LatLng(parseFloat(this.location.latitude), parseFloat(this.location.longitude));
 
    let mapOptions = {
      center: this.latLng,
      zoom: 16,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    }
 
    this.map = new google.maps.Map(this.mapElement.nativeElement, mapOptions);
    
    let marker = new google.maps.Marker({
      map: this.map,
      animation: google.maps.Animation.DROP,
      position: this.map.getCenter()
    });
   
    let content = `<h4>${this.name}</h4>`;         
   
    this.addInfoWindow(marker, content);
  }
  
  addInfoWindow(marker, content){
 
    let infoWindow = new google.maps.InfoWindow({
      content: content
    });
   
    google.maps.event.addListener(marker, 'click', () => {
      infoWindow.open(this.map, marker);
    });
   
  }
}