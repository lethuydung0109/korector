import {Component, Input, OnInit} from '@angular/core';
import {Criteria} from "../model/criteria";
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
  public  deleteCriteria() {
    this.criteriaService.deleteCriteria(this.criteria.id).subscribe(
      data=> {
        console.log(data);
        this.listComponent.reloadData();
      },
      error => console.log(error)
    );
  }

}
