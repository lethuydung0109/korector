import { Component, OnInit } from '@angular/core';
import {CriteriaGroup} from "../classes/criteriaGroup";
import {ActivatedRoute, Router} from "@angular/router";
import {CriteriaGroupService} from "../_services/criteria-group.service";

@Component({
  selector: 'app-update-criteria-group',
  templateUrl: './update-criteria-group.component.html',
  styleUrls: ['./update-criteria-group.component.scss']
})
export class UpdateCriteriaGroupComponent implements OnInit {
  id: number;
  criteriaGroup: CriteriaGroup;

  constructor(private route: ActivatedRoute, private router:Router, private criteriaGroupService: CriteriaGroupService) { }

  ngOnInit(): void {
    this.criteriaGroup= new CriteriaGroup();
    this.id= this.route.snapshot.params['id'];
    /**this.criteriaGroupService.getCriteriaGroup(this.id).subscribe(
      data=>{
        console.log(data)
        this.criteriaGroup=data;
      }, error=>console.log(error)
    );**/
  }

  updateCriteriaGroup() {
    /**this.criteriaGroupService.updateCriteriaGroup(this.id, this.criteriaGroup).subscribe(
      data =>console.log(data),  error =>console.log(error)
    );**/
    this.criteriaGroup= new CriteriaGroup();
    this.gotoList();
  }

  onSubmit() {
    this.updateCriteriaGroup();
  }

  private gotoList() {
    this.router.navigate(['/criteriaGroups'])
  }
}
