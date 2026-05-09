import { Component, OnInit } from '@angular/core';
import { NavigationEnd, RouterModule, RouterOutlet, Router } from '@angular/router';
import { LoginService } from '../app/service/login';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterModule],
  templateUrl:"./app.html",
  styleUrls: ["./app.css"]
})

export class App implements OnInit {

  username: string = "";
  dropdownOpen = false;
  isLoggedIn = false;

  constructor(
    private login: LoginService,
    private router: Router
  ) {}

  ngOnInit(){

    this.updateNavbar();
    this.router.events.subscribe(event => {
      if(event instanceof NavigationEnd){
        this.updateNavbar();
      }
    });

  }

  updateNavbar(){
    this.username = this.login.getUsername();

    if(this.username && this.username !== ""){
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }

  toggleDropdown(){
    this.dropdownOpen = !this.dropdownOpen;
  }

}