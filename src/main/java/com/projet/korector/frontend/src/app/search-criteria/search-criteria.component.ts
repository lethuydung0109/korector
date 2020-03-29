import { Component, OnInit } from '@angular/core';
import {Criteria} from "../model/criteria";
import {CriteriaService} from "../_services/criteria.service";

@Component({
  selector: 'app-search-criteria',
  templateUrl: './search-criteria.component.html',
  styleUrls: ['./search-criteria.component.scss']
})
export class SearchCriteriaComponent implements OnInit {

  id: number;
  criteria: Criteria;
  constructor(private  dataService: CriteriaService) { }

  ngOnInit(): void {
    this.id = null;
  }
  private  searchCriteria(){
    this.dataService.searchCriteria(this.id).subscribe(
      criteria => this.criteria
    );
  }
  onSubmit() {
    this.searchCriteria();
  }

}
