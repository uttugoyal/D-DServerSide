import {Injectable} from '@angular/core';
import {LoginRequest} from '../models/login-request';
import {HttpClient} from '@angular/common/http';
import {User} from '../models/user';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';
import {JwtHelperService} from '@auth0/angular-jwt'



@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: User;
  jwtHelper = new JwtHelperService();
  isLoggedIn:boolean=false;
  constructor(private http: HttpClient,) {
  }

  login(loginRequest: LoginRequest): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}/signin`, loginRequest);
  }

  register(user: User): Observable<any> {
    return this.http.post<any>(`${environment.apiUrl}/signup`, user);
  }

  forgetPassword(email): Observable<any> {
    const formData = new FormData();
    formData.append('email', email);
    return this.http.post<any>(`${environment.apiUrl}/forgotPassword`, formData);
  }

  updatePassword(token, password): Observable<any> {
    const formData = new FormData();
    formData.append('token', token);
    formData.append('password', password);
    return this.http.post<any>(`${environment.apiUrl}/reset`, formData);
  }

  logout(){
    localStorage.clear()
  }

  isAuthenticated(): boolean {
    //return !this.jwtHelper.isTokenExpired(this.getUser().token);
    return true;
  }

  saveUser(user){
    localStorage.setItem('currentUser', JSON.stringify(user));
  }

  getUser(){
    return new User(JSON.parse(localStorage.getItem('currentUser')));
  }

  getRole() {
    //return new User(JSON.parse(localStorage.getItem('currentUser'))).roles[0];
    return 'ROLE_ADMIN'
  }

  getUserId() {
    //return new User(JSON.parse(localStorage.getItem('currentUser'))).userId;
  }

  getToken(){
    console.log( new User(JSON.parse(localStorage.getItem('currentUser'))).token);
    return new User(JSON.parse(localStorage.getItem('currentUser'))).token;
  }
}
