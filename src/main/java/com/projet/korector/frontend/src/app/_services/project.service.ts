import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from "../classes/user";
import {environment} from "../../environments/environment";

const AUTH_API = 'http://localhost:8085/api/ressource';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  public url =environment.api_url;
  constructor(private http: HttpClient) { }

  getProjectById(id: number): Observable<any> {
    console.log("AUTH " + AUTH_API);
    return this.http.get(this.url+'/project/'+id);
  }

  getProject(id: number): Observable<any> {
    console.log("AUTH " + AUTH_API);

    return this.http.get(this.url+'/getProject/'+id);
  }


  deleteProject(id: number): Observable<any> {
    return this.http.delete(this.url+'/deleteProject/'+id, { responseType: 'text' });
  }

  getProjectList(): Observable<any> {
    return this.http.get(this.url+'/allProjects');
  }
}
