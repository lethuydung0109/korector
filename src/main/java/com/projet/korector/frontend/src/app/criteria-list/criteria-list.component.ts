import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-criteria-list',
  templateUrl: './criteria-list.component.html',
  styleUrls: ['./criteria-list.component.scss']
})
export class CriteriaListComponent implements OnInit {

  criteriaList: Observable<Criteria[]>;
  constructor(private  criteriaService: CriteriaService,private router: Router) {

  }

  ngOnInit(): void {
    this.reloadData();
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
