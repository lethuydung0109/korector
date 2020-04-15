import { Component, OnInit } from '@angular/core';
import {Observable} from "rxjs";
import {CriteriaGroup} from "../classes/criteriaGroup";
import {CriteriaGroupService} from "../services/criteria-group.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-criteria-group-list',
  templateUrl: './criteria-group-list.component.html',
  styleUrls: ['./criteria-group-list.component.scss']
})
export class CriteriaGroupListComponent implements OnInit {
  criteriaGroups: Observable<CriteriaGroup[]>;

  constructor(private criteriaGroupService: CriteriaGroupService, private router : Router ) { }

  ngOnInit(): void {
    this.reloadData();
  }

  private reloadData() {
    this.criteriaGroups = this.criteriaGroupService.getCriteriaGroupList();
  }
  deleteCriteriaGroup(name: string) {
    /**this.criteriaGroupService.deleteCriteriaGroup(name).subsribe(
      data =>{
        console.log(data);
        this.reloadData();
      },
      error=> console.log(error));
     **/
  }
  criteriaGroupDetails(name: string) {
    this.router.navigate(['details', name]);
  }
}
