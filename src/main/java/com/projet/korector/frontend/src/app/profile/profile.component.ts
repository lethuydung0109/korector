import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
const AUTH_API = 'http://localhost:8080/';
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  username = '';
  name = '';
  imageURL = '';
  email = '';
  error = '';
  roles: string[] = [];
  isLoggedIn = false;
  isLoginFailed = false;
  toReload = true;
  constructor(private http: HttpClient, 
    private tokenStorage: TokenStorageService) { }

  ngOnInit(){
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' , 
      'Authorization' : 'Bearer ' + this.tokenStorage.getToken()})
    };
    this.http.get(AUTH_API + 'user/me', httpOptions).subscribe(
      data => {
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;
        this.username = this.tokenStorage.getUser().username;
        this.name = this.tokenStorage.getUser().name;
        this.imageURL = this.tokenStorage.getUser().imageUrl;
        this.email = this.tokenStorage.getUser().email;
        console.log(data)
        
      },
      err => {
        this.error = err.error.message;
        this.isLoginFailed = true;
      }
    )
  //   if(this.toReload){
  //     window.location.reload();
  //     this.toReload = false;
  // }
    
  }

}
