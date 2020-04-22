import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import {User} from "../classes/user";

const AUTH_API = 'http://localhost:8080/api/ressource';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) { }

  getProjectById(id: number): Observable<any> {
    return this.http.get(`${AUTH_API}/project/${id}`);
  }

  getProject(id: number): Observable<any> {
    return this.http.get(`${AUTH_API}/getProject/${id}`);
  }


  deleteProject(id: number): Observable<any> {
    return this.http.delete(`${AUTH_API}/deleteProject/${id}`, { responseType: 'text' });
  }

  getProjectList(): Observable<any> {
    return this.http.get(`${AUTH_API}/allProjects`);
  }
}
