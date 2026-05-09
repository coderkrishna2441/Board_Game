import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  apiUrl = "http://localhost:8080/signup";

  constructor(private http: HttpClient) {}

  registerUser(user: any){
    return this.http.post(this.apiUrl, user);
  }

}