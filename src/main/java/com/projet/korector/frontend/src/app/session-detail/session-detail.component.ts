import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../classes/project';
import { SessionService } from '../_services/session.service';
import { Session } from '../classes/session';
import { ProjectService } from '../_services/project.service';
import { FormControl } from '@angular/forms';
import { RunService } from '../_services/run.service';
import { Run } from '../classes/run';
import { DatePipe } from '@angular/common';
import { Criteria } from '../classes/criteria';
import { CriteriaService } from '../_services/criteria.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ValidationModalComponent } from '../validation-modal/validation-modal.component';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.scss']
})
export class SessionDetailComponent implements OnInit {

  public sessionId: number;
  public sessionName : string ="";
  public sessionDateDepot : string="";
  public sessionHeureDepot : string="";
  public hideView : boolean = true;
  public typeCritere : string;
  public updateSessionId : number = 0;

  public statiqueCiterias : Array<Criteria> =[];
  public dynamiqueCiterias : Array<Criteria> =[];
  public sessionProjects : Array<Project>=[]; 
  public sessionCriterias : Array<Criteria>=[];
  public allProjects : Array<Project>=[];
  public sessionRuns : Array<Run>=[];
  public newCriteria : Array<Criteria> =[];

  public nameSession: string;
  public nameCritere : string;
  public valueCritere: number;

  constructor(private actRoute: ActivatedRoute, 
              private sessionService : SessionService, 
              private projectService : ProjectService,
              private runService : RunService,
              public datepipe: DatePipe,
              private criteriaService : CriteriaService,
              private modalService: NgbModal) {
    this.sessionId = this.actRoute.snapshot.params.id;
    this.typeCritere='static';
    this.nameCritere="critere";
  }

  ngOnInit(): void {

    this.sessionService.getSessionById(this.sessionId).subscribe(data => {
      this.sessionId=data.id;
      this.sessionName=data.name.valueOf();
      this.sessionDateDepot=data.date_depot.valueOf();  
      this.sessionHeureDepot=data.heureDepot.valueOf();    
    });
  
    let listProjects : Array<Project> =[];
    this.sessionService.getSessionProjects(this.sessionId).subscribe(data => {
      data.forEach(p => { listProjects.push(p); })
    });
    this.sessionProjects=listProjects;

    let listCriterias : Array<Criteria> =[];
    this.sessionService.getSessionCriterias(this.sessionId).subscribe(data => {
      data.forEach(c => { listCriterias.push(c); })
    });
    this.sessionCriterias=listCriterias;

    let projectList: Array<Project>=[];
    this.projectService.getProjectList().subscribe(data => {
      data.forEach(p => { projectList.push(p); })
    });
    this.allProjects=projectList;

    let runs: Array<Run>=[];
    this.sessionService.getSessionRuns(this.sessionId).subscribe(data => {
      data.forEach(r => { runs.push(r); })
    });
    this.sessionRuns=runs;

    let listStaticCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Statique').subscribe(data => {
      data.forEach(c => {
        listStaticCriteria.push(c);
      })
    });
    this.statiqueCiterias=listStaticCriteria;

    let listDynamicCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Dynamique').subscribe(data => {
      data.forEach(c => {
        listDynamicCriteria.push(c);
      })
    });
    this.dynamiqueCiterias=listDynamicCriteria;
  }

  public updateSession() : void
  {
    let nameSession : string =document.getElementsByName("nameSession")[0]["value"];
    this.sessionName=nameSession;
    let dateDepotSession : string = document.getElementsByName("dateDepotSession")[0]["value"];
    let heureDepotSession : string = document.getElementsByName("heureDepotSession")[0]["value"];
    let updateSession : Session = new Session(nameSession,dateDepotSession,heureDepotSession);
    updateSession.id=this.sessionId;
    updateSession.projects=this.sessionProjects;
    updateSession.criterias=this.sessionCriterias;

    this.sessionCriterias.forEach(c => {
      if(!this.newCriteria.includes(c)) c.value=document.getElementsByName("criteriaValue_"+c.id)[0]["value"];      
      this.criteriaService.updateCriteria(c.id,c).subscribe(data=>{ c=data; });
    });

    this.sessionService.updateSession(updateSession).subscribe(data => {
      this.updateSessionId=data.id;
      if(this.updateSessionId!=0) this.openValidationModal(); 
    });
  }

  public addToCriteriaList() : void
  {
    let criteria : Criteria;
    if(this.typeCritere=="static")
    {
      criteria = this.statiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;
      if(!this.sessionCriterias.includes(criteria)) 
      {
        this.sessionCriterias.push(criteria);
        this.newCriteria.push(criteria);
      }
    }
    else if (this.typeCritere=="dynamic")
    {
      criteria = this.dynamiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;
      if(!this.sessionCriterias.includes(criteria)) 
      {
        this.sessionCriterias.push(criteria);    
        this.newCriteria.push(criteria);
      }
    }
  }

  public deleteCriteriaFromSelectedList(criteria : Criteria) : void
  {
    this.sessionCriterias.splice(this.sessionCriterias.indexOf(criteria),1);
    if(!this.newCriteria.includes(criteria))  this.newCriteria.splice(this.sessionCriterias.indexOf(criteria),1);
  }

  public addToProjectList(project :Project) : void
  {
    this.sessionProjects.push(project);
  }

  public deleteToProjectList(project : Project) : void
  {
    this.sessionProjects.splice(this.sessionProjects.indexOf(project),1);
  }

  public isHidden() : void { this.hideView=false; }

  public changeCritereType(type : string) : void { this.typeCritere=type; }

  public createRun() : void
  {
    this.runService.createRun(this.sessionId).subscribe( data => {
      this.sessionRuns.push(data);
    });  
  }

  public exportCSV(runId : number) : void
  {
    this.sessionService.exportCSV(runId).subscribe( response => {
      let date = new Date();
      this.datepipe.transform(date, 'ddMMyyyyHHmmss');
      var blob = new Blob([response], {type: 'text/csv' })
      const link = document.createElement('a');
      link.href = window.URL.createObjectURL(new Blob([response], {type: 'text/csv'}));
      link.download = `run_${date}.csv`;
      link.click();
    });
  }

  public openValidationModal() : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = 'Modifications sauvegardés !';
  }

}
