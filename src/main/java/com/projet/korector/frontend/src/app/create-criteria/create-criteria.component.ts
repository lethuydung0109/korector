import { Component, OnInit } from '@angular/core';
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service"
import {ActivatedRoute, Router} from "@angular/router";

// @ts-ignore
@Component({
  selector: 'app-create-criteria',
  templateUrl: './create-criteria.component.html',
  styleUrls: ['./create-criteria.component.scss']
})
export class CreateCriteriaComponent implements OnInit {

  criteria: Criteria = new Criteria();
  submitted =  false;
  constructor(private  criteriaService : CriteriaService,private router :Router, private  route: ActivatedRoute ) { }

  ngOnInit(): void {
  }

  newCriteria(): void {
    this.submitted=false;
    this.criteria = new Criteria();
  }

  save() {
    this.criteria= new Criteria();
    this.criteria.name= (document.getElementById("name") as HTMLInputElement).value;
    this.criteria.type =(document.getElementById("type") as HTMLInputElement).value;
    this.criteria.value =parseFloat((document.getElementById("value") as HTMLInputElement).value);
    this.criteria.url=(document.getElementById("url") as HTMLInputElement).value

    this.criteriaService.createCriteria(this.criteria).subscribe(
      data=> console.log(data),
      error => console.log(error)
    );
  }
  onSubmit() {
    this.submitted = true;
    this.save();
  }

  changeCriteriaType(type: string) {
    this.criteria.type= type;
  }

  verifyInput( url: string) {

    if((document.getElementById("type") as HTMLInputElement).value = "Statique") {
      (document.getElementById(url) as HTMLInputElement).disabled = false;
    }else {
      (document.getElementById(url) as HTMLInputElement).disabled = true;
    }
  }
  list() {
    this.router.navigate(['Criteria']);
  }
}
