import { Component, ChangeDetectorRef } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { LoginService } from '../../service/login';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-memberpage',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: "memberpage.html",
  styleUrl: "memberpage.css"
})
export class MemberpageComponent {

  username: any;
  memberData: any = { user: {}, events: [], member_types: [] };
  dropdownOpen: boolean = false;
  eventname: string = '';

  constructor(
    private loginService: LoginService,
    private http: HttpClient,
    private cd: ChangeDetectorRef,
  ) {

    this.username = this.loginService.getUsername();

    const storedUser = sessionStorage.getItem('memberUser');
    const storedEvents = sessionStorage.getItem('memberEvents');
    const storedTypes = sessionStorage.getItem('memberTypes');

    if (storedUser) {
      this.memberData.user = JSON.parse(storedUser);
    }

    if (storedEvents) {
      this.memberData.events = JSON.parse(storedEvents);
    }

    if (storedTypes) {
      this.memberData.member_types = JSON.parse(storedTypes);
    }

    console.log("Loaded from sessionStorage");

    this.http.get<any>("http://localhost:8080/member/" + this.username)
      .subscribe(
        data => {

          console.log("API DATA:", data);

          this.memberData.user = data.user;
          this.memberData.events = data.events;
          this.memberData.member_types = data.member_types;

          sessionStorage.setItem('memberUser', JSON.stringify(data.user));
          sessionStorage.setItem('memberEvents', JSON.stringify(data.events));
          sessionStorage.setItem('memberTypes', JSON.stringify(data.member_types));

          this.cd.detectChanges();
        },
        (error: any) => {
          console.error("Failed to load member data:", error);
        }
      );
  }

  toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }

  closeDropdown() {
    this.dropdownOpen = false;
  }

}