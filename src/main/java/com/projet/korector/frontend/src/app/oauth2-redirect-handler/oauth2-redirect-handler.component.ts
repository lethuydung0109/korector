import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import {Router} from "@angular/router"
import { IfStmt } from '@angular/compiler';
import { HttpClient, HttpHeaders } from '@angular/common/http';
const AUTH_API = 'http://localhost:8080/';

@Component({
  selector: 'app-oauth2-redirect-handler',
  templateUrl: './oauth2-redirect-handler.component.html',
  styleUrls: ['./oauth2-redirect-handler.component.scss']
})
export class Oauth2RedirectHandlerComponent implements OnInit {

  token = '';
  
  constructor(private tokenStorage: TokenStorageService, 
    private router: Router, private http: HttpClient) { 
    
  }

  ngOnInit(): void {
    this.token = this.getUrlParameter('token');
    
    if(this.token){
      this.tokenStorage.saveToken(this.token);
      this.tokenStorage.toggleFLAGREF();
      this.router.navigate(['/profile']);

    }else{
      this.router.navigate(['/login'])
    }

  }

  getUrlParameter(name) {
    console.log("Here");
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');

    var results = regex.exec(window.location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
}

  reloadPage() {
    window.location.reload();
  }

}
