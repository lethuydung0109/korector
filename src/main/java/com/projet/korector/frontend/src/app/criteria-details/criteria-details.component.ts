import {Component, Input, OnInit} from '@angular/core';
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";
import {CriteriaListComponent} from "../criteria-list/criteria-list.component";

@Component({
  selector: 'app-criteria-details',
  templateUrl: './criteria-details.component.html',
  styleUrls: ['./criteria-details.component.scss']
})
export class CriteriaDetailsComponent implements OnInit {

  @Input() criteria: Criteria;
  constructor(private  criteriaService: CriteriaService, private listComponent: CriteriaListComponent) { }

  ngOnInit(): void {
  }


}
