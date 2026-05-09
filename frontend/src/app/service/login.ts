import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  apiUrl = "http://localhost:8080/login";
  username: string = '';

  constructor(private http: HttpClient) {}

  login(data: { username: string; password: string }) {

    // ✅ SAFE CHECK
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.setItem('currentUsername', data.username);
    }

    return this.http.post(this.apiUrl, data);
  }

  getUsername() {

    // ✅ SAFE ACCESS
    if (typeof window !== 'undefined' && window.localStorage) {
      return localStorage.getItem('currentUsername') || '';
    }

    return ''; // fallback for SSR
  }

  logout() {
    if (typeof window !== 'undefined' && window.localStorage) {
      localStorage.removeItem('currentUsername');
    }
  }
}