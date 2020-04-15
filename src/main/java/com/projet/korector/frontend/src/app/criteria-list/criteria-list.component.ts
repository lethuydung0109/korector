import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";

@Component({
  selector: 'app-criteria-list',
  templateUrl: './criteria-list.component.html',
  styleUrls: ['./criteria-list.component.scss']
})
export class CriteriaListComponent implements OnInit {

  criteriaList: Observable<Criteria[]>;
  constructor(private  criteriaService: CriteriaService) {

  }

  ngOnInit(): void {
    this.reloadData();
  }
  public reloadData() {
    this.criteriaList = this.criteriaService.getCriteriaList();
  }

  criteriaDetails(id: number) {

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

  }
}
