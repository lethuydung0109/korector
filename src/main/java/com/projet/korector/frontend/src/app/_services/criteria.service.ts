import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

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
