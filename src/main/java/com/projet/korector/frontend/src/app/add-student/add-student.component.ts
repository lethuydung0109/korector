import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { environment } from 'src/environments/environment';
import { NgForm } from "@angular/forms";
import { NgxSpinnerService } from 'ngx-spinner';

import {User} from '../classes/user';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-add-student',
  templateUrl: './add-student.component.html',
  styleUrls: ['./add-student.component.scss']
})
export class AddStudentComponent implements OnInit {
   std : User;
   model = new User(); 

   submitted = false;


   public actionButton: string = 'Save';



   constructor(private route: ActivatedRoute, 
          private router: Router,private userService: UserService, private spinner: NgxSpinnerService) { 

   this.std = new User();
 
   }
   ngOnInit(): void {
  }

   /**
  * Method that save in the Backend server,
  *  a new customer data retreived from the form
  * @param addCustomerForm
  */




   createStudent() : void {
    this.userService.saveUser(this.model)
      .subscribe( data => {
        console.log(data);
        alert("User created successfully.");
      });
      
          this.submitted = true; 
        
      }  
  
    // TODO: Remove this when we're done
    get diagnostic() { return JSON.stringify(this.model); }

}



