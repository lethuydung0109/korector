import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { NgForm } from "@angular/forms";
import { NgxSpinnerService } from 'ngx-spinner';

import {User} from '../classes/user';
import {SectionName} from '../classes/section-name';

import { UserService } from '../services/user.service';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-add-teacher',
  templateUrl: './add-teacher.component.html',
  styleUrls: ['./add-teacher.component.scss']
})
export class AddTeacherComponent implements OnInit {
  std : User;
  model = new User();

   submitted = false;
   public actionButton: string = 'Save';
   public sections: Array <SectionName> = [];
   sectionSelected : SectionName;
  



   constructor( private router: Router,private teacherService : UserService,private adminService : AdminService) { 
 //   this.std = new Student();
 
 
   }
   ngOnInit(): void {

    let letSections: Array <SectionName> = [];
    this.adminService.getAllSections().subscribe(sections =>{
      sections.forEach(element => {

        letSections.push(element);

       // return;
     

      console.log(element);
    });

  });
  this.sections = letSections;

  }


   createTeacher() : void {
    this.teacherService.saveTeacher(this.model)
      .subscribe( data => {
        console.log(data);
        alert("User created successfully.");
      });
      
          this.submitted = true; }
  
  
  
  
    // TODO: Remove this when we're done
    get diagnostic() { return JSON.stringify(this.model); }

}


