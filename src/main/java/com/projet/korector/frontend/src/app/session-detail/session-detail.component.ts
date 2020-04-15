import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../classes/project';
import { SessionService } from '../services/session.service';
import { Session } from '../classes/session';
import { ProjectService } from '../_services/project.service';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.scss']
})
export class SessionDetailComponent implements OnInit {

  public sessionId: Number;
  public sessionName : string ="";
  public sessionDateDepot : string="";

  public sessionProjects : Array<Project>=[];
  public allProjects : Array<Project>=[];


  constructor(private actRoute: ActivatedRoute, private sessionService : SessionService, private projectService : ProjectService) {
    this.sessionId = this.actRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    
    let sessionId : Number =0;
    let sessionName : string ="";
    let sessionDateDepot : string="";
    this.sessionService.getSessionById(this.sessionId).subscribe(data => {

      console.log("data ", data);
      this.sessionId=data.id;
      this.sessionName=data.name.valueOf();
      //this.sessionDateDepot=data.dateDepot.valueOf();
    });
  
     let listProjects : Array<Project> =[];
     this.sessionService.getSessionProjects(this.sessionId).subscribe(data => {
       console.log("data",data)
       data.forEach(p => {
        listProjects.push(p);
       })
     });
     this.sessionProjects=listProjects;

    let projectList: Array<Project>=[];
    this.projectService.getProjectList().subscribe(data => {
      data.forEach(p => {
        projectList.push(p);
      })
    });
    console.log("projects :", projectList)
    this.allProjects=projectList;
  }

  public addProjectToSession(project : Project) : void
  {
    this.sessionService.addProjectToSession(project, this.sessionId).subscribe(data => {})
    this.sessionProjects.push(project);
  }

  public deleteProjectFromSession(project : Project) : void
  {
    this.sessionService.deleteProjectFromSession(project.id, this.sessionId).subscribe(data => {})
    this.sessionProjects.splice(this.sessionProjects.indexOf(project),1);
  }
}
