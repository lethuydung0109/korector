import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

const AUTH_API = 'http://localhost:8080/api/Criteria';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


@Injectable({
  providedIn: 'root'
})
export class CriteriaService {
  private  baseUrl = environment.api_url;
  constructor(private http: HttpClient) { }

  public  createCriteria (criteria: Object): Observable<Object> {
    return  this.http.post(`${this.baseUrl}`+`/createCriteria`, criteria);
  }
  public  updateCriteria(id: number, value: any) : Observable<Object>{
    return this.http.put(`${this.baseUrl}/updateCriteria/${id}`,value);
  }
  public  deleteCriteria(id:number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteCriteria/${id}`,{responseType: 'text'});
  }
  public searchCriteria(id: number): Observable<Object> {
    return this.http.get(`${this.baseUrl}/researchCriteria/${id}`)
  }
  public getCriteriaList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
