import { Component, OnInit } from '@angular/core';
import { AdminService } from '../services/admin.service';
import {Router } from '@angular/router';
import { findLast } from '@angular/compiler/src/directive_resolver';

@Component({
  selector: 'app-stats',
  templateUrl: './stats.component.html',
  styleUrls: ['./stats.component.scss']
})
export class StatsComponent implements OnInit {
  nb_students : number;
  nb_profs : number;
  nb_classes : number;

  /********** Services ************/
 // private adminService : AdminService;


  constructor(private router: Router,private adminService : AdminService) { }

  ngOnInit(): void {

    this.adminService.getstatsResponse().subscribe(
      data =>{
        var jsonData = JSON.parse(data);

        this.nb_students = jsonData.nb_students;
        this.nb_profs =  jsonData.nb_profs;
        this.nb_classes = jsonData.nb_classes;

        console.log(data);
       // alert( this.nb_students);
      } ) ;
    }

  /*
  getStats() : void{
    this.adminService.getstatsResponse().subscribe(
      data =>{
        var jsonData = JSON.parse(data);

        this.nb_students = jsonData.nb_students;
        this.nb_profs =  jsonData.nb_profs;
        this.nb_classes = jsonData.nb_classes;

        console.log(data);
       // alert( this.nb_students);
      }
    ); */


 
  } 


