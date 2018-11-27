import { Events } from 'ionic-angular';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class BadgeProvider{
    count = new Subject<any>();
     constructor( public events: Events ) { }


    updateCount(): Observable<any> {
        return this.count.asObservable();
    }
}