import { Component, OnInit } from '@angular/core';
import {Router } from '@angular/router';
import { environment } from 'src/environments/environment';

import {User} from '../classes/user';
import { UserService } from '../services/user.service';
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

   constructor( private router: Router,private studentService : UserService) { 
 //   this.std = new Student();
 
   }

   createStudent() : void {
    this.studentService.saveUser(this.model)
      .subscribe( data => {
        console.log(data);
        alert("User created successfully.");
      });
      
          this.submitted = true; }
  
  
  
  
    // TODO: Remove this when we're done
    get diagnostic() { return JSON.stringify(this.model); }
  ngOnInit(): void {
  }

}


