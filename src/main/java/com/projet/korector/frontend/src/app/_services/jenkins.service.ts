import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from "@angular/common/http";


const httpOptions = {
  headers: new HttpHeaders(
  {
     'Content-Type': 'application/json'
  })
}
const API_URL = 'http://localhost:8085/api/jenkins/';
@Injectable({
  providedIn: 'root'
})
export class JenkinsService {

  constructor(private http: HttpClient) { }


public runProject (buildName : Number , urlName : String, sessionId : Number, projectId : Number ){

  console.log ("URL " + this.http.post(API_URL + 'build/' + buildName + "/" +urlName , httpOptions) );
  return this.http.post(API_URL + 'build/' + buildName + "/" +urlName + "/" + sessionId + "/" + projectId , httpOptions);

}
}