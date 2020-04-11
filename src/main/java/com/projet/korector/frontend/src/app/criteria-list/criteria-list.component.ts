import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {Criteria} from "../model/criteria";
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
}
