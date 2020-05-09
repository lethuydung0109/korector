import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Session } from '../classes/session';
import { Project } from '../classes/project';
import { Run } from '../classes/run';
import { Criteria } from '../classes/criteria';
import { SessionCritere } from '../classes/session-critere';

@Injectable({
  providedIn: 'root'
})
export class SessionService {

  public url =environment.api_url;
  constructor(private http: HttpClient) {
  }

  public createSession(session : Session) : Observable<Session>
  {
    const routeQuery=this.url+"/createSession";
    console.log("session", session)

    console.log("session dans service : ", session)
    return this.http.post<Session>(routeQuery,session);
  }

  public updateSession(session: Session) : Observable<any>
  {
    const routeQuery=this.url+"/updateSession";
    console.log("updateSession", session)

    return this.http.put<Session>(routeQuery,session);
  }

  public getSessionById(sessionId : Number) : Observable<Session>
  {
    const routeQuery=this.url+"/sessionById/"+sessionId;
    return this.http.get<Session>(routeQuery);
  }

  public getAllSessions() : Observable<Array<Session>>
  {
    const routeQuery=this.url+"/allSessions";
    return this.http.get<Array<Session>>(routeQuery);
  }

  public getAllSessionsByUser() : Observable<Array<Session>>
  {
    const routeQuery=this.url+"/user/sessions";
    return this.http.get<Array<Session>>(routeQuery);
  }

  public getSessionProjects(sessionId : Number) : Observable<Array<Project>>
  {
    let routeQuery=this.url+"/sessionProjects/"+sessionId;
    return this.http.get<Array<Project>>(routeQuery);
  }

  public getSessionCriterias(sessionId : Number) : Observable<Array<Criteria>>
  {
    let routeQuery=this.url+"/sessionCriterias/"+sessionId;
    return this.http.get<Array<Criteria>>(routeQuery);
  }

  public getSessionCriteres(sessionId : number) : Observable<Array<SessionCritere>>
  {
    let routeQuery=this.url+"/getSessionCriteres/"+sessionId;
    return this.http.get<Array<SessionCritere>>(routeQuery);
  }

  public deleteSession (sessionId :Number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteSession/"+sessionId;
    return this.http.delete(routeQuery);
  }

  //A implementer dans backend
  public deleteAllSessions () : Observable<any> {
    const routeQuery=this.url+"/deleteAllSessions";
    return this.http.delete(routeQuery);
  }

  public addProjectToSession(project : Project, sessionId : Number) : Observable<any>
  {
    const routeQuery=this.url+"/addProjectToSession/"+sessionId+"/"+project.id;
    return this.http.put(routeQuery,project);
  }

  public deleteProjectFromSession(projectId : Number, sessionId : Number) : Observable<any>
  {
    const routeQuery=this.url+"/deleteProjectFromSession/"+sessionId+"/"+projectId;
    return this.http.delete(routeQuery);
  }

  public getSessionRuns(sessionId : number) : Observable<Array<Run>>
  {
    const routeQuery=this.url+"/getSessionRuns/"+sessionId;
    return this.http.get<Array<Run>>(routeQuery);
  }

  public exportCSV(runId : number) : Observable<any>
  {
    let headers = new HttpHeaders();
    headers = headers.set('Accept', 'application/csv');
    let routeQuery=this.url+"/exportCSV/"+runId;
    return this.http.get<any>(routeQuery,{headers: headers});
  }

}
