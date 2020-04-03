import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import {Router } from '@angular/router';
import { User } from '../classes/user';

@Component({
  selector: 'app-profs-stats',
  templateUrl: './profs-stats.component.html',
  styleUrls: ['./profs-stats.component.scss']
})
export class ProfsStatsComponent implements OnInit {

  public profs: Array <User> = [];


  /********** Services ************/
  //private adminService : AdminService;
   constructor(private router: Router,private adminService : AdminService) { }
  ngOnInit(): void {

    let letProfs: Array <User> = [];
    this.adminService.findAllProf().subscribe(users =>{
      users.forEach(element => {

        letProfs.push(element);
      console.log(element);
    });

  });
  this.profs = letProfs;
  }

  deleteUserById(user : User) : void{
    console.log("delete user : ",user.id)
    this.adminService.deleteUser(user.id).subscribe(() => console.log("user deleted"));
    this.profs.splice(this.profs.indexOf(user),1);

  }

}
