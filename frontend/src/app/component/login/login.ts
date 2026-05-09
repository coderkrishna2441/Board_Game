import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../service/login';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: "login.html",
  styleUrl: "login.css",
})
export class LoginComponent {

  username: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  Login(usernameInput: string, passwordInput: string) {

    this.loginService.login({
      username: usernameInput,
      password: passwordInput
    }).subscribe(

      (response: any) => {

        if (response.message === "Login Success") {

          alert("✅ Login Successful!\n"+"Click OK");

          this.router.navigate(['member', usernameInput]);

        } else {

          alert("❌ Invalid Username or Password!\n"+"⚠️ Enter correct password or username");

        }

      },

      (error: any) => {

        if (error.error && error.error.message) {
          alert("⚠️ " + error.error.message);
        } else {
          alert("⚠️ Enter correct password or username");
        }

      }
    );
  }
}