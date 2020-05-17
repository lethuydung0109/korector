import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {TokenStorageService} from "../_services/token-storage.service";

@Component({
  selector: 'app-criteria-list',
  templateUrl: './criteria-list.component.html',
  styleUrls: ['./criteria-list.component.scss']
})
export class CriteriaListComponent implements OnInit {

  criteriaList: Observable<Criteria[]>;
  public userRole: string;
  constructor(private  criteriaService: CriteriaService,private router: Router,private http: HttpClient,  private tokenStorage: TokenStorageService) {

  }

  ngOnInit(): void {
    this.reloadData();
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' ,
        'Authorization' : 'Bearer ' + this.tokenStorage.getToken()})
    };
    this.http.get(environment.api_base_url + '/user/me', httpOptions).subscribe(
      data => {
        this.tokenStorage.saveUser(data);
        this.userRole = this.tokenStorage.getUser().roles.map(x => x.name).join(',');
        this.userRole = this.userRole.replace("ROLE_", "");
        console.log("profile",this.userRole)
      });
  }
  public reloadData() {
    this.criteriaList = this.criteriaService.getCriteriaList();
    console.log(this.criteriaList);
  }

  criteriaDetails(id: number) {
    this.router.navigate(['criteria-details', id]);
  }

  deleteCriteria(id: number) {
    this.criteriaService.deleteCriteria(id).subscribe(
      data=> {
        console.log(data);
        this.reloadData();
      },
      error => console.log(error)
    );
  }

  updateCriteria(id: number) {
    this.router.navigate(['update-criteria', id]);
  }
}
