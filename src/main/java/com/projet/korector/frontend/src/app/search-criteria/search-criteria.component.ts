import { Component, OnInit } from '@angular/core';
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs";

@Component({
  selector: 'app-search-criteria',
  templateUrl: './search-criteria.component.html',
  styleUrls: ['./search-criteria.component.scss']
})
export class SearchCriteriaComponent implements OnInit {

  name: string;
  type: string;
  criteriaList: any;
  submitted: boolean =false;

  constructor(private route: ActivatedRoute,private router: Router, private service: CriteriaService ) { }

  ngOnInit(): void {
  }
  private  searchCriteria() {
    this.name = (document.getElementById("name") as HTMLInputElement).value;
    this.type = (document.getElementById("type") as HTMLInputElement).value;

    if (this.type != ""  && this.name!= ""){
      this.service.searchCriteria(this.name,this.type).subscribe(
        data => {
          console.log(data)
          this.criteriaList = data;
        }, error => console.log(error)
      );
    }else if(this.type!= "" ) {
      this.service.searchCriteriaByType(this.type).subscribe(
        data => {
          console.log(data)
          this.criteriaList =data;
          }, error => console.log(error)
      );
    }else if(this.name!= "") {
      this.service.searchCriteriaByName(this.name).subscribe(
        data => {
          console.log(data)
          this.criteriaList =data;
        }, error => console.log(error)
      );
    }

  }
  changeCriteriaType(type: string) {
    this.type = type;
  }
  onSubmit() {
    this.submitted = true;
    this.searchCriteria();
  }

}
