import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from 'rxjs';


import { User } from '../classes/user';
import {environment} from "../../environments/environment";
const API_URL_BASE = environment.api_url ;

const API_URL = API_URL_BASE + '/user/';

const API_URL2 = API_URL_BASE + '/resource/';

allStudent : User   ;
const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'student', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'prof', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'admin', { responseType: 'text' });
  }

  public saveUser(user : User , userRoleId : Number  ) : Observable <User> {
    // const routeQuery=this.url+"/user/saveUser";
 
      console.log("User dans service " +  JSON.stringify(user) );
 
 
         return this.http.post<User>( API_URL + 'saveUser/' + userRoleId, {
           username: user.username,
           password: user.password,
           email : user.email,
           githubAccount : user.githubAccount,
           sectionName : user.sectionName
         },httpOptions);
 
 
 
 }
}
