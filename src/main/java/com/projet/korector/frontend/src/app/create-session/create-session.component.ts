import {Component, OnInit} from '@angular/core';
import {Project} from '../classes/project';
import {Criteria} from '../classes/criteria';
import {Session} from '../classes/session';
import {SessionService} from '../services/session.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatCardModule} from '@angular/material/card';
import { ProjectService } from '../_services/project.service';

@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss']
})
export class CreateSessionComponent implements OnInit {

  public projects : Array<Project>=[new Project("p1"), new Project("p2"), new Project("p3"), new Project("p4")];
  public criterias : Array<Criteria> =[new Criteria("c1"), new Criteria("c2"), new Criteria("c3"), new Criteria("c4")];
  public selectedProjects : Array<Project> = [];
  public selectedCriteria : Array<Criteria> = [];
  public typeSession : string;

  constructor(private sessionService : SessionService, private projectService : ProjectService) {
    this.typeSession = 'normal';
  }

  ngOnInit(): void {

    // let listProjects: Array<Project>;
    // this.projectService.getProjectList().subscribe(data => {
    //   data.forEach(p => {
    //     listProjects.push(p);
    //   })
    // });
    // this.projects=listProjects;

  }

  public addProjetToSelectedProject(p : Project) : void{

    if(!this.selectedProjects.includes(p)) this.selectedProjects.push(p);
    else console.log('déjà sélectionné');
  }

  public addCriteriaToSelectedCriteria(c : Criteria) : void{
    if(!this.selectedCriteria.includes(c)) this.selectedCriteria.push(c);
    else console.log('déjà sélectionné');
  }

  public changeSessionType(type: string): void
  {
    this.typeSession=type;
  }

  public createSession(): void{
    let nameSession : string =document.getElementsByName("nameSession")[0]["value"];
    let date : Date = new Date(document.getElementsByName("date")[0]["value"]);
   // console.log("date",document.getElementsByName("date")[0]["value"]);
    let createSession = new Session(nameSession, date);
    //createSession.projects=this.selectedProjects;
    //createSession.criteria=this.selectedCriteria;

    this.sessionService.createSession(createSession).subscribe(
      data =>{
      alert("Session created successfully. see your list of sessions in tab Session");
      console.log("data", data);

    });

      }

  public retrieveProjectToselected(project : Project) : void
  {
    this.selectedProjects.splice(this.selectedProjects.indexOf(project),1);
  }
}
