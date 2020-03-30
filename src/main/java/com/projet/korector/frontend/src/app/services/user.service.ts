



import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { User } from '../classes/user';
import { Observable } from 'rxjs';
import {  retry, catchError } from 'rxjs/operators'
import { environment } from 'src/environments/environment';

const API_URL = 'http://localhost:8080/api/user/saveUser/';
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
  private usersUrl: string;
   constructor(private http: HttpClient) { 
        this.usersUrl = 'http://localhost:8080/api/user/saveUser';

   }


   public saveUser(user : User ) : Observable <User> {
   // const routeQuery=this.url+"/user/saveUser";

     console.log("User dans sservice " +  JSON.stringify(user) );
     
    
        return this.http.post<User>(this.usersUrl , {
          username: user.username,
          password: user.password,
          email : user.email,
          githubAccount : user.githubAccount
        },httpOptions);



}



    //return this.http.post<Student>(this.userUrl, std);

    //return this.http.post<User>(API_URL , std);

    //return this.http.post<Customer>('/library/rest/customer/api/addCustomer', customer);

   
   //}
  
  

}
