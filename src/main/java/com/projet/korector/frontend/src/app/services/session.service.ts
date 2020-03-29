import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Session } from '../classes/session';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public url =environment.api_url;
  constructor(private http: HttpClient) { 
  }

  public createSession(session : Session) : Observable<Session>
  {
    const routeQuery="session/"+session;
    return this.http.get<Session>(routeQuery); 
  }
}
