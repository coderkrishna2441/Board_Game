import { Component,OnInit,ChangeDetectorRef} from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import {LoginService} from '../../service/login';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import {EventDataService} from '../../service/event';

@Component({
  selector: 'app-eventinfo-component',
  standalone: true,
  imports: [RouterModule,CommonModule],

  templateUrl:"eventinfo-component.html",

  styleUrl:"eventinfo-component.css"
})

export class EventinfoComponent {

  eventname:string='';
  username:string='';
  dropdownOpen:boolean = false;
  eventData: any = {
  event_name: "",
  host_names: [],
  event_date: "",
  event_time: "",
  event_venue: "",
  event_description: "",
  poster:"",
  event_schedule: [],
}

  constructor(
    private route: ActivatedRoute,
    private loginService: LoginService,
    private http: HttpClient,
    private cd:ChangeDetectorRef,
    private event:EventDataService,
  ) {}

  ngOnInit() {

  this.username = this.loginService.getUsername();

  this.route.paramMap.subscribe(params => {

    // Decode URL parameter
    this.eventname = decodeURIComponent(params.get('eventname') || '');

    console.log("Event Name:", this.eventname);

    this.event.setEventName(this.eventname);

    // Encode before sending to backend
    const encodedEvent = encodeURIComponent(this.eventname);

    this.http.get<any>("http://localhost:8080/eventinfo/" + encodedEvent)
      .subscribe(data => {

        console.log("API DATA:", data);

        this.eventData = data;

        this.cd.detectChanges();

      });

  });

}

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }
  closeDropdown() {
    this.dropdownOpen = false;
  }
}