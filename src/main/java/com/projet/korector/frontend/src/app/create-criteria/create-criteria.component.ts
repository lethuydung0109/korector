import { Component, OnInit } from '@angular/core';
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service"

@Component({
  selector: 'app-create-criteria',
  templateUrl: './create-criteria.component.html',
  styleUrls: ['./create-criteria.component.scss']
})
export class CreateCriteriaComponent implements OnInit {

  criteria: Criteria = new Criteria();
  submitted =  false;
  constructor(private  criteriaService : CriteriaService) { }

  ngOnInit(): void {
  }

  newCriteria(): void {
    this.submitted=false;
    this.criteria = new Criteria();
  }

  save() {
    this.criteriaService.createCriteria(this.criteria).subscribe(
      data=> console.log(data), error => console.log(error)
    );
    this.criteria= new Criteria();
  }
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  changeCriteriaType(type: string) {
    this.criteria.type= type;

  }
  verifyInput(type: string, url: string) {
    if(type != "Dynamique") {
      (document.getElementById(url) as HTMLInputElement).disabled = false;
    }
  }
}
