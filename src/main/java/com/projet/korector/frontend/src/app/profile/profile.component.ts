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
  htmlURL = '';
  error = '';
  roles: String;
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
        this.roles = this.tokenStorage.getUser().roles.map(x => x.name).join(',');
        this.roles = this.roles.replace("ROLE_", "");
        this.username = this.tokenStorage.getUser().username;
        this.name = this.tokenStorage.getUser().name;
        this.imageURL = this.tokenStorage.getUser().imageUrl;
        if(this.imageURL == null){
          this.imageURL = 'https://img.icons8.com/clouds/2x/user.png';
        }
        console.log(this.imageURL);
        this.email = this.tokenStorage.getUser().email;
        this.htmlURL = this.tokenStorage.getUser().githubAccount;
        console.log(data);
        console.log(this.roles);
        console.log(JSON.stringify(this.tokenStorage.getUser().roles));
        if(this.name == ''){
          this.name = 'Name not provided';
        }
        if(this.email == ''){
          this.email  = 'Email not provided';
        }
      },
      err => {
        this.error = err.error.message;
        this.isLoginFailed = true;
      }
    )
  //   if(window.location.search !== '?loaded' ) {
  //     window.location.search = '?loaded';
  //     window.location.reload();
  // }
  //   if(this.toReload){
  //     window.location.reload();
  //     this.toReload = false;
  // }
    
  }

}
