import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.scss']
})
export class NavComponent implements OnInit {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username: string;
  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(){
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();

      this.roles = user.roles.slice(0,1);
      console.log("roles",this.roles);
     console.log(this.roles[0]);
     if( this.roles[0] == "ROLE_ENSEIGNANT") {
       this.showModeratorBoard=true;
     }
      if( this.roles[0] == "ROLE_ADMIN") {
        this.showAdminBoard=true;
      }
      this.username = user.username;
      console.log("User name  = " + this.username);

  }
}

logout() {
  this.tokenStorageService.signOut();
  window.location.reload();
}

}
