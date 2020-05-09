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
import { SessionCritere } from '../classes/session-critere';
import { SessionCritereService } from '../_services/session-critere.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { environment } from 'src/environments/environment';

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
  public sessionCriteres : Array<SessionCritere>=[];
  public allProjects : Array<Project>=[];
  public sessionRuns : Array<Run>=[];
  public newCriteria : Array<SessionCritere> =[];

  public nameSession: string;
  public nameCritere : string;
  public valueCritere: number;
  public poidsCritere: number;
  public seuilCritere: number;
  public userRole: string;

  constructor(private actRoute: ActivatedRoute, 
              private sessionService : SessionService, 
              private projectService : ProjectService,
              private runService : RunService,
              public datepipe: DatePipe,
              private criteriaService : CriteriaService,
              private modalService: NgbModal,
              private sessionCritereService: SessionCritereService,
              private http: HttpClient, 
              private tokenStorage: TokenStorageService) {

    this.sessionId = this.actRoute.snapshot.params.id;
    this.typeCritere='Statique';
    this.seuilCritere=0;
  }

  ngOnInit(): void {

    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' , 
      'Authorization' : 'Bearer ' + this.tokenStorage.getToken()})
    };
    this.http.get(environment.api_base_url + '/user/me', httpOptions).subscribe(
      data => {
        this.tokenStorage.saveUser(data);      
        this.userRole = this.tokenStorage.getUser().roles.map(x => x.name).join(',');
        this.userRole = this.userRole.replace("ROLE_", "");
        console.log("profile",this.userRole)
      });
      
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

    let listSessionCriteres : Array<SessionCritere> =[];
    this.sessionService.getSessionCriteres(this.sessionId).subscribe(data => {
      data.forEach(sc => { listSessionCriteres.push(sc); })
    });
    this.sessionCriteres=listSessionCriteres;

    console.log("sessionCriteres :", this.sessionCriteres)

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
      if(data!=null)
      {
        data.forEach(c => {
          listStaticCriteria.push(c);
        })
      }
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
    let dateDepotSession : string = "";
    let heureDepotSession : string = "";

    if(this.userRole!="ETUDIANT")
    {
      dateDepotSession = document.getElementsByName("dateDepotSession")[0]["value"];
      heureDepotSession = document.getElementsByName("heureDepotSession")[0]["value"];
    }
    
    let updateSession : Session = new Session(nameSession,dateDepotSession,heureDepotSession);
    updateSession.id=this.sessionId;

    let sessionProjectIds: Array<number>=[];
    let sessionCritereIds: Array<number>=[];
    
    this.sessionProjects.forEach(p=>{ sessionProjectIds.push(p.id) });

    let pourcentageTotal:number=0;
    this.sessionCriteres.forEach(c => {
      if(!this.newCriteria.includes(c))
      {
        c.height=parseInt(document.getElementsByName("sessionCritereHeight_"+c.id)[0]["value"]);
        if(this.userRole!="ETUDIANT")
        {
          c.seuil=parseInt(document.getElementsByName("sessionCritereSeuil_"+c.id)[0]["value"]);
        }
        else c.seuil=0;
        this.sessionCritereService.updateSessionCritere(c.id,c.height,c.seuil).subscribe(data=>{ 
         console.log("data")
         });
      }         
      pourcentageTotal=pourcentageTotal+c.height;
    });

    this.sessionCriteres.forEach(c=>{ 
      sessionCritereIds.push(c.id)
      console.log("SC : ",c)
    });

    updateSession.projects=sessionProjectIds;
    updateSession.sessionCritere=sessionCritereIds;

    if(pourcentageTotal===100)
    {
      console.log("updated session : ",updateSession);
      this.sessionService.updateSession(updateSession).subscribe(data => {
        this.updateSessionId=data.id;
        if(this.updateSessionId!=0) this.openValidationModal("Modifications sauvegardées"); 
      });      
    }
    else {
      this.openValidationModal("La somme des pourcentages de vos critères n'est pas égal à 100");
    }
  }

  public addToCriteriaList() : void
  {
    let criteria : Criteria;
    let sessionCritere : SessionCritere = new SessionCritere();
    if(this.typeCritere=="Statique")
    {
      criteria = this.statiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      sessionCritere.critereId=criteria.id;
      sessionCritere.name=criteria.name;
      sessionCritere.sessionId=this.sessionId;
      sessionCritere.height=this.poidsCritere;
      sessionCritere.type=criteria.type;

      if(this.userRole!="ETUDIANT")
      {
        sessionCritere.seuil=this.seuilCritere;
      }else sessionCritere.seuil=0;

      this.sessionCritereService.createSessionCritere(sessionCritere).subscribe(data=>{
        console.log(data)
        this.sessionCriteres.push(data);
        this.addCriteriaToList(data);
      });
    }
    else if (this.typeCritere=="Dynamique")
    {
      criteria = this.dynamiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      sessionCritere.critereId=criteria.id;
      sessionCritere.name=criteria.name;
      sessionCritere.sessionId=this.sessionId;
      sessionCritere.height=this.poidsCritere;
      sessionCritere.type=criteria.type;

      if(this.userRole!="ETUDIANT")
      {
        sessionCritere.seuil=this.seuilCritere;
      }else sessionCritere.seuil=0;
      this.sessionCritereService.createSessionCritere(sessionCritere).subscribe(data=>{
        this.sessionCriteres.push(data);
        this.addCriteriaToList(data);
      });
    }
  }

  public addCriteriaToList(c : SessionCritere) : void {
    if(!this.newCriteria.includes(c)) this.newCriteria.push(c);
    else this.openValidationModal("Ce critère est déjà dans la liste");
  }

  public deleteSessionCriteriaFromSelectedList(sessionCritere : SessionCritere) : void
  {
    this.sessionCriteres.splice(this.sessionCriteres.indexOf(sessionCritere),1);
    if(!this.newCriteria.includes(sessionCritere))  this.newCriteria.splice(this.sessionCriteres.indexOf(sessionCritere),1);
    this.sessionCritereService.deleteSessionCritere(this.sessionId,sessionCritere.id).subscribe(data=>{});
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

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = message;
  }

}
