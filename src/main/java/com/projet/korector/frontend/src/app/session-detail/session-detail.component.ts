import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../classes/project';
import { SessionService } from '../services/session.service';
import { Session } from '../classes/session';
import { ProjectService } from '../_services/project.service';
import { FormControl } from '@angular/forms';
import { RunService } from '../services/run.service';
import { Run } from '../classes/run';
import { DatePipe } from '@angular/common';
import { Criteria } from '../classes/criteria';
//import { saveAs } from 'file-saver/FileSaver';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.scss']
})
export class SessionDetailComponent implements OnInit {

  public sessionId: number;
  public sessionName : string ="";
  //public sessionDateDepot : string="";
  public sessionDateDepot : any;

  public sessionProjects : Array<Project>=[]; 
  public sessionCriterias : Array<Criteria>=[];
  public allProjects : Array<Project>=[];
  public sessionRuns : Array<Run>=[];
  public tab : Array<Session>=[];
  public currentSession : Session = new Session("","");


  constructor(private actRoute: ActivatedRoute, 
              private sessionService : SessionService, 
              private projectService : ProjectService,
              private runService : RunService,
              public datepipe: DatePipe) {
    this.sessionId = this.actRoute.snapshot.params.id;
  }

  ngOnInit(): void {
    
    let sessionId : number =0;
    let sessionName : string ="";
    let sessionDateDepot : string="";
    this.sessionService.getSessionById(this.sessionId).subscribe(data => {

      console.log("data ", data);
      this.sessionId=data.id;
      this.sessionName=data.name.valueOf();
      this.sessionDateDepot=data.date_depot.valueOf();
      
    });
    //this.sessionDateDepot = "16/04/2020";
  
     let listProjects : Array<Project> =[];
     this.sessionService.getSessionProjects(this.sessionId).subscribe(data => {
       console.log("data",data)
       data.forEach(p => {
        listProjects.push(p);
       })
     });
     this.sessionProjects=listProjects;

     let listCriterias : Array<Criteria> =[];
     this.sessionService.getSessionCriterias(this.sessionId).subscribe(data => {
       console.log("sessionCriterias",data)
       data.forEach(c => {
        listCriterias.push(c);
       })
     });
     this.sessionCriterias=listCriterias;

    let projectList: Array<Project>=[];
    this.projectService.getProjectList().subscribe(data => {
      data.forEach(p => {
        projectList.push(p);
      })
    });
    console.log("projects :", projectList)
    this.allProjects=projectList;

    let runs: Array<Run>=[];
    this.sessionService.getSessionRuns(this.sessionId).subscribe(data => {
      data.forEach(r => {
        runs.push(r);
      })
    });
    console.log("runs :", runs)
    this.sessionRuns=runs;
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

  public updateSession() : void
  {
    let nameSession : string =document.getElementsByName("nameSession")[0]["value"];
    let dateDepotSession :string = document.getElementsByName("dateDepotSession")[0]["value"];
    let updateSession :Session = new Session(nameSession,dateDepotSession);
    updateSession.id=this.sessionId;
    updateSession.projects=this.sessionProjects;

    this.sessionService.updateSession(updateSession).subscribe(data => {});

  }

  public createRun() : void
  {
    
    // let tab : Array<Session>=[];
    // this.sessionService.getSessionById(this.sessionId).subscribe((data : Session) =>{
    //   this.tab.push(data);
    //   this.currentSession.setSession(data);
    //   console.log("data",data);
    //   console.log("data_run",this.tab);
    // });
    // console.log("currentSession",this.currentSession.getId());
    // let session : Session=this.tab.pop();
    // console.log("session",session);
    // let run : Run = new Run(session);
    this.runService.createRun(this.sessionId).subscribe( data => {
      this.sessionRuns.push(data);
      console.log("run",data)});  
    }

  public exportCSV(runId : number) : void
  {
    this.sessionService.exportCSV(runId).subscribe( response => {
      console.log("data", response)
      let date = new Date();
      this.datepipe.transform(date, 'ddMMyyyyHHmmss');
      var blob = new Blob([response], {type: 'text/csv' })
    //  saveAs(blob, "myFile.csv");
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(new Blob([response], {type: 'text/csv'}));
      link.download = `run_${date}.csv`;
      link.click();

    });
  }

}
