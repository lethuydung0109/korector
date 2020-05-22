



import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from '../classes/user';
//import { SectionName } from '../classes/section-name';
import { Observable } from 'rxjs';
import {  retry, catchError } from 'rxjs/operators'
import {environment} from "../../environments/environment";
const API_URL = environment.api_url + '/user';
// const API_URL = 'http://localhost:8085/api/user/';

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
  //public url =environment.api_url;
   constructor(private http: HttpClient) {


   }


   public saveUser(user : User , userRoleId : Number  ) : Observable <User> {
   // const routeQuery=this.url+"/user/saveUser";

     console.log("User dans sservice " +  JSON.stringify(user) );


        return this.http.post<User>( API_URL + 'saveUser/' + userRoleId, {
          username: user.username,
          password: user.password,
          email : user.email,
          githubAccount : user.githubAccount,
          sectionName : user.sectionName
        },httpOptions);



}
/*
public saveUser(user : User ) : Observable <User> {
  // const routeQuery=this.url+"/user/saveUser";

    console.log("User dans sservice " +  JSON.stringify(user) );


       return this.http.post<User>( API_URL + 'saveTeacher', {
         username: user.username,
         password: user.password,
         email : user.email,
         githubAccount : user.githubAccount
       },httpOptions);



} */


    //return this.http.post<Student>(this.userUrl, std);

    //return this.http.post<User>(API_URL , std);

    //return this.http.post<Customer>('/library/rest/customer/api/addCustomer', customer);


   //}



}
