import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  apiUrl = "http://localhost:8080/edituserinfo";

  constructor(private http: HttpClient) {}

  updateProfile(username: string, user: any):any {
    return this.http.put(`${this.apiUrl}/${username}`, user);
  }

  getProfile(username: string):any {
    return this.http.get(`${this.apiUrl}/${username}`);
  }
}