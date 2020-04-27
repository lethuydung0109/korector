import { Injectable } from '@angular/core';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const FLAG_REF = 'refresh-page';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor() { }

  signOut() {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.removeItem(FLAG_REF);
    // window.sessionStorage.clear();
  }

  public saveToken(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.removeItem(FLAG_REF);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser() {
    return JSON.parse(sessionStorage.getItem(USER_KEY));
  }

  public getFLAGREF(){
    return window.sessionStorage.getItem(FLAG_REF);
  }

  // help to flag when the page Profile needs to refresh once and only once
  public toggleFLAGREF(){
    console.log(this.getFLAGREF());
    if(this.getFLAGREF() == null){
      window.sessionStorage.setItem(FLAG_REF, "true");
    }
    else if(this.getFLAGREF() == "true"){
      window.sessionStorage.setItem(FLAG_REF, "false");
    }else{
      window.sessionStorage.setItem(FLAG_REF, "true");
    }
    console.log(this.getFLAGREF());
  }

}