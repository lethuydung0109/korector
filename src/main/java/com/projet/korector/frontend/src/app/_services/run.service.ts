import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Run } from '../classes/run';

const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'})
}
const API_URL_JENSON = 'http://localhost:8085/api/jenkins/build/';
@Injectable({
  providedIn: 'root'
})
export class RunService {

  public url =`${environment.api_url}/run`;
  constructor(private http: HttpClient) {
  }

  public createRun(sessionId : number) : Observable<Run>
  {
    let routeQuery=this.url+"/createRun/"+sessionId;
    return this.http.get<Run>(routeQuery);
  }
public sonarQubeRun(buildName : String , urlName : String) : Observable <any> {
  // s = API_URL_JENSON  + buildName + "/" +urlName
  console.log("Url" + API_URL_JENSON  + buildName + "/" +urlName);
  console.log (this.http.post<any>( API_URL_JENSON  + buildName + "/" +urlName,  httpOptions));
  return this.http.post<any> ( API_URL_JENSON  + buildName + "/" +urlName,  httpOptions);
}

  // public exportCSV() : Observable<any>
  // {
  //   let headers = new HttpHeaders();
  //   headers = headers.set('Accept', 'application/csv');
  //   let routeQuery=this.url+"/exportCSV";
  //   return this.http.get<any>(routeQuery,{headers: headers});
  // }
}
