import { Component, OnInit } from '@angular/core';
import { AdminService } from '../_services/admin.service';
import {Router } from '@angular/router';
import { User } from '../classes/user';


@Component({
  selector: 'app-student-stats',
  templateUrl: './student-stats.component.html',
  styleUrls: ['./student-stats.component.scss']
})
export class StudentStatsComponent implements OnInit {
  public students: Array <User> = [];


 /********** Services ************/
 //private adminService : AdminService;
  constructor(private router: Router,private adminService : AdminService) { }


  ngOnInit( ): void {
    let letStudents: Array <User> = [];
    this.adminService.findAllStudent().subscribe(users =>{
      users.forEach(element => {

        letStudents.push(element);

       // return;
     

      console.log(element);
     // alert( this.nb_students);
    });

  });
  this.students = letStudents;


  }

  deleteUserById(user : User) : void{
    console.log("delete user : ",user.id)
    this.adminService.deleteUser(user.id).subscribe(() => console.log("user deleted"));
    this.students.splice(this.students.indexOf(user),1);

  }

}
