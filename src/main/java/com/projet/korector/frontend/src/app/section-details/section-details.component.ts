import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Section} from "../classes/section";
import {SectionService} from "../_services/section.service";
import {Observable} from "rxjs";
import {User} from "../classes/user";

@Component({
  selector: 'app-section-detail',
  templateUrl: './section-details.component.html',
  styleUrls: ['./section-details.component.scss']
})
export class SectionDetailComponent implements OnInit {

  sectionId: number;
  section: any;
  teachers: Array <User> = [];
  students: Array <User> = [];

  constructor(private actRoute: ActivatedRoute, private  service: SectionService) {
  }

  ngOnInit(): void {
    this.sectionId = this.actRoute.snapshot.params.id;
    this.service.getSectionById(this.sectionId).subscribe(data => {
      this.section = data;
    }, error => console.log(error));

    this.service.getTeachers().subscribe(users => {
      users.forEach(element => {
        this.teachers.push(element);
        console.log(element);
      });
    });

        this.service.getStudents().subscribe(u => {
        u.forEach(element => {
        this.students.push(element);
        console.log(element);
      });
  });
  }

}
