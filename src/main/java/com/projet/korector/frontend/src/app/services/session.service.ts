import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Session } from '../classes/session';
import { Project } from '../classes/project';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public url =environment.api_url;
  constructor(private http: HttpClient) {
  }

  public createSession(session : Session) : Observable<Session>
  {
    const routeQuery=this.url+"/createSession/"+session;

    console.log("session dans service : ", session)
    return this.http.post<Session>(routeQuery,session);
  }

  // by user
  public getAllSessions() : Observable<Array<Session>>
  {
    const routeQuery=this.url+"/allSessions";
    return this.http.get<Array<Session>>(routeQuery);
  }

  public getSessionProjects(sessionId : Number) : Observable<Array<Project>>
  {
    let routeQuery=this.url+"/sessionProjects/"+sessionId;
    return this.http.get<Array<Project>>(routeQuery);
  }

  public deleteSession (sessionId :Number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteSession/"+sessionId;
    return this.http.delete(routeQuery);
  }

  public deleteAllSessions () {
    const routeQuery=this.url+"/deleteAllSessions";
    return this.http.delete(routeQuery);
  }
}
