import {Component, Input, OnInit} from '@angular/core';
import {Criteria} from "../classes/criteria";
import {CriteriaService} from "../_services/criteria.service";
import {CriteriaListComponent} from "../criteria-list/criteria-list.component";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-criteria-details',
  templateUrl: './criteria-details.component.html',
  styleUrls: ['./criteria-details.component.scss']
})
export class CriteriaDetailsComponent implements OnInit {

  id: number;
  criteria: any;

  constructor(private  criteriaService: CriteriaService, private router :Router, private  route: ActivatedRoute) { }

  ngOnInit(): void {
    this.criteria= new Criteria();
    this.id = this.route.snapshot.params['id'];
    this.criteriaService.getCriteriaById(this.id).subscribe(
      data => {
        console.log(data);
        this.criteria=data;
      }, error => console.log(error));
  }


  list() {
    this.router.navigate(['Criteria']);
  }
}
