import { Component, OnInit } from '@angular/core';
import {CriteriaGroup} from "../classes/criteriaGroup";
import {CriteriaGroupService} from "../_services/criteria-group.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-create-criteria-group',
  templateUrl: './create-criteria-group.component.html',
  styleUrls: ['./create-criteria-group.component.scss']
})
export class CreateCriteriaGroupComponent implements OnInit {
  criteriaGroup: CriteriaGroup = new CriteriaGroup();
  submitted =false;

  constructor(private criteriaGroupService: CriteriaGroupService, private router:Router) { }

  ngOnInit(): void {
  }

  newCriteriaGroup():void {
    this.submitted=false;
    this.criteriaGroup= new CriteriaGroup();
  }

  save (){
   /** this.criteriaGroupService.createCriteriaGroup(this.criteriaGroup).subscribe(
      data=>console.log(data), error =>console.log(error)
    );
    this.criteriaGroup= new CriteriaGroup();
    this.gotoList();**/
  }

  onSubmit() {
    this.submitted =true;
    this.save();
  }

  private gotoList() {
    this.router.navigate(['/criteriaGroups'])
  }
}
