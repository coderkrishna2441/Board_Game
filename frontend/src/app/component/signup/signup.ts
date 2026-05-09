import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { SignupService } from '../../service/signup';

@Component({
  selector: 'app-signup',
  imports: [CommonModule, FormsModule, RouterModule],
  styleUrl: "signup.css",
  templateUrl: "signup.html"
})
export class SignupComponent {

  constructor(
    private signupService: SignupService,
    private router: Router
  ) {}

  model = {
    firstname: '',
    lastname: '',
    email: '',
    phonenumber: '',
    dob: '',
    username: '',
    password: ''
  };

  emailError: string = '';
  usernameError: string = '';
  phoneError: string = '';
  message: String = '';

  data: { [key: string]: string } = {};

  email_pattern = /[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2}/;
  phonenumber_pattern = /^[0-9]{10}$/;

  submit(
    firstname: string,
    lastname: string,
    email: string,
    phonenumber: string,
    dob: string,
    username: string,
    password: string
  ) {

    this.emailError = '';
    this.usernameError = '';
    this.phoneError = '';
    this.message = '';

    if (
      firstname != '' && lastname != '' && email != '' &&
      phonenumber != '' && dob != '' && username != '' && password != ''
    ) {

      if (
        firstname.length >= 3 &&
        username.length >= 4 &&
        password.length >= 6 &&
        phonenumber.length === 10
      ) {

        if (
          this.email_pattern.test(email) &&
          this.phonenumber_pattern.test(phonenumber)
        ) {

          this.data = {
            firstname,
            lastname,
            email,
            phonenumber,
            dob,
            username,
            password
          };

          console.log(this.data);

          this.signupService.registerUser(this.data).subscribe({
            next: (res) => {
              console.log("Success:", res);

              this.router.navigate(['login']);
            },

            error: (err) => {
                console.log("Error:", err);

                const errors = err?.error?.error;

                if (errors) {
                  alert(errors);
                } else {
                  this.message = "Something went wrong";
                }
              }

          });

        }
      }
    }
  }
}