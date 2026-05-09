import { Component,ChangeDetectorRef } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule} from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-userinfoview',
  standalone: true,
  imports: [RouterModule, HttpClientModule],
  templateUrl:"user-profile-display-component.html",
styleUrl:"user-profile-display-component.css"
})

export class UserInfoViewComponent {

username:string="";
dropdownOpen:boolean=false;

profile:any={
pfp:"assets/images/user_logo.jpg",
firstname:"",
lastname:"",
address:"",
phone:"",
membertype:"",
email:"",
bio:""
};

constructor(private http:HttpClient, private route:ActivatedRoute,private cd:ChangeDetectorRef){

this.username = this.route.snapshot.paramMap.get('username') || "";

this.http.get<any>("http://localhost:8080/userprofile/" + this.username)
.subscribe(data=>{
this.profile.firstname=data.first_name;
this.profile.lastname=data.last_name;
this.profile.phone=data.phone_number;
this.profile.membertype=data.member_type_name;
this.profile.email=data.email;
this.profile.address=data.address;
this.profile.bio=data.bio;
this.cd.detectChanges();
});

}

toggleDropdown() {
    this.dropdownOpen = !this.dropdownOpen;
  }
closeDropdown() {
    this.dropdownOpen = false;
  }

}