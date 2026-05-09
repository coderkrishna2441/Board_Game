import { Injectable } from '@angular/core';
import {LoginService} from './login';

@Injectable({
  providedIn: 'root'
})
export class EventDataService {

  constructor(private login:LoginService){}

  setEventName(name: string) {

    console.log("SET EVENT NAME:", name);

    localStorage.setItem("currentEventName", name);
  }

  getEventName() {

    const eventName = localStorage.getItem("currentEventName") || '';

    console.log("GET EVENT NAME:", eventName);
    console.log("USERNAME:",this.login.getUsername())

    return eventName;
  }

  clearEventName(){
    localStorage.removeItem("currentEventName");
  }

}