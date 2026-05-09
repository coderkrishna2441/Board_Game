import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { ProfileService } from '../../service/editprofile';
import { LoginService } from '../../service/login';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-userprofile',
  standalone: true,
  imports: [FormsModule, RouterModule, CommonModule],
  templateUrl: "userprofile.html",
  styleUrl: "userprofile.css"
})
export class UserProfileComponent implements OnInit {

  dropdownOpen: boolean = false;
  username: string = '';
  isLoaded: boolean = true; // keep true (no hiding)

  model = {
    username: '',
    first_name: '',
    last_name: '',
    address: '',
    phone_number: '',
    member_type_name: '',
    email: '',
    bio: ''
  };

  data: { [key: string]: string } = {};

  constructor(
    private profileService: ProfileService,
    private router: Router,
    private login: LoginService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {

    // ✅ GET USERNAME PROPERLY
    this.username = this.login.getUsername();

    // fallback if needed from route
    if (!this.username) {
      this.username = this.route.snapshot.paramMap.get('username') || '';
    }

    console.log("Username:", this.username);

    // ✅ LOAD DATA IMMEDIATELY
    this.loadProfile();
  }

  loadProfile() {

    this.profileService.getProfile(this.username).subscribe(

      (response: any) => {
        console.log("API Response:", response);

        if (response) {
          Object.assign(this.model, response);
        }
      },

      (error: any) => {
        alert("⚠️ Error loading profile");
        console.error(error);
      }

    );
  }

  submit(form: any) {

    if (form.invalid) {
      alert("⚠️ Please fill all required fields correctly!");
      return;
    }

    this.data = { ...this.model };

    Object.keys(this.data).forEach(key => {
      if (!this.data[key]) delete this.data[key];
    });

    this.profileService.updateProfile(this.username, this.data).subscribe(

      (response: any) => {
        alert("✅ Profile Updated Successfully!");
        this.router.navigate(['/userinfo', this.username]);
      },

      (error: any) => {
        if (error.error && error.error.message) {
          alert("❌ " + error.error.message);
        } else {
          alert("❌ Update Failed!");
        }
        console.error(error);
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