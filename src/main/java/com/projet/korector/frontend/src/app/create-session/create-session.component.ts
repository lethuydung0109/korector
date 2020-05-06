import {Component, OnInit} from '@angular/core';
import {Project} from '../classes/project';
import {Criteria} from '../classes/criteria';
import {Session} from '../classes/session';
import {SessionService} from '../_services/session.service';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatSelectModule} from '@angular/material/select';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import {MatCardModule} from '@angular/material/card';
import { ProjectService } from '../_services/project.service';
import { CriteriaService } from '../_services/criteria.service';
import { Router } from '@angular/router';
import { ValidationModalComponent } from '../validation-modal/validation-modal.component';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
  selector: 'app-create-session',
  templateUrl: './create-session.component.html',
  styleUrls: ['./create-session.component.scss']
})
export class CreateSessionComponent implements OnInit {

  //public projects : Array<Project>=[new Project("p1"), new Project("p2"), new Project("p3"), new Project("p4")];
  public projects : Array<Project>=[];
  public statiqueCiterias : Array<Criteria> =[];
  public dynamiqueCiterias : Array<Criteria> =[];
  public selectedProjects : Array<Project> = [];
  public selectedCriteria : Array<Criteria> = [];
  //public selectedDynamicCriteria : Array<Criteria> = [];
  public typeSession : string;
  public typeCritere : string;
  public listStaticCriteria: Array<Criteria> =[];

  public nameSession: string;
  public nameCritere : string;
  public valueCritere: number;
  public heureDepot : string;

  constructor(private router :Router,
              private modalService: NgbModal,
              private sessionService : SessionService, 
              private projectService : ProjectService, 
              private criteriaService : CriteriaService) {
    this.typeSession='normal';
    this.typeCritere='static';
    this.nameCritere="critere";
  }

  ngOnInit(): void {

    let listProjects: Array<Project>=[];
    this.projectService.getProjectList().subscribe(data => {
      data.forEach(p => {
        listProjects.push(p);
      })
    });
    console.log("projects :", listProjects)
    this.projects=listProjects;

    let listStaticCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Statique').subscribe(data => {
      data.forEach(c => {
        listStaticCriteria.push(c);
      })
    });
    console.log("critere :", listStaticCriteria)
    this.statiqueCiterias=listStaticCriteria;

    let listDynamicCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Dynamique').subscribe(data => {
      data.forEach(c => {
        listDynamicCriteria.push(c);
      })
    });
    console.log("critere :", listDynamicCriteria);
    this.dynamiqueCiterias=listDynamicCriteria;

  }

  public createSession(): void
  {
    let nameSession : string = document.getElementsByName("nameSession")[0]["value"];
    let dateDepot:string="";
    let heureDepot:string="";

    if(this.typeSession=="depot") 
    {
      dateDepot  = document.getElementsByName("date")[0]["value"];
      heureDepot  = document.getElementsByName("heure")[0]["value"];
    }

    let createSession = new Session(nameSession, dateDepot, heureDepot);

    let selectedProjectIds: Array<number>=[];
    let selectedCriteriaIds: Array<number>=[];
    let pourcentageTotal=0;

    this.selectedProjects.forEach(p=>{ selectedProjectIds.push(p.id)});
    this.selectedCriteria.forEach(c=>{ 
      selectedCriteriaIds.push(c.id)
      pourcentageTotal=pourcentageTotal+c.value;
    });

    createSession.projects=selectedProjectIds;
    createSession.criterias=selectedCriteriaIds;
    
    if(this.selectedCriteria.length == 0)
    {
      this.openValidationModal("Aucun critère sélectionné. Impossible de créer une session");
    }
    else if(pourcentageTotal>100)
    {
      this.openValidationModal("La somme des pourcentages de vos critères dépasse 100");
    }
    else{
      console.log("session à créer  : ", createSession);
      this.sessionService.createSession(createSession).subscribe(data =>{
        if(data.id!=null) 
        {
          this.openValidationModal("La session a bien été créée");
          this.router.navigate(['session']);
        }
        else this.openValidationModal("Une erreur est survenue. Veuillez contacter le support.");
      });
      
    }    
  }

  public addProjetToSelectedProject(p : Project) : void{
    if(!this.selectedProjects.includes(p)) this.selectedProjects.push(p);
    else console.log("déjà sélectionné");
  }

  public addCriteriaToSelectedCriteria(c : Criteria) : void{
    if(!this.selectedCriteria.includes(c)) this.selectedCriteria.push(c);
    else console.log("déjà sélectionné");
  }

  public changeNameCritere(name : string) : void { this.nameCritere=name; }

  public addCriteria() : void
  {
    let criteria : Criteria;
    if(this.typeCritere=="static")
    {
      criteria = this.statiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;

      this.criteriaService.updateCriteria(criteria.id,criteria).subscribe(data=>{criteria=data});
      this.addCriteriaToSelectedCriteria(criteria);
    }
    else if (this.typeCritere=="dynamic")
    {
      criteria = this.dynamiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;
      
      this.criteriaService.updateCriteria(criteria.id,criteria).subscribe(data=>{criteria=data});
      console.log("criteria",criteria);
      this.addCriteriaToSelectedCriteria(criteria);
    }
  }

  public changeSessionType(type : string) : void { this.typeSession=type; }

  public changeCritereType(type : string) : void { this.typeCritere=type; }

  public retrieveProjectToselected(project : Project) : void
  {
    this.selectedProjects.splice(this.selectedProjects.indexOf(project),1);
  }

  public retrieveCriteriaToSelected(criteria : Criteria) : void
  {
    this.selectedCriteria.splice(this.selectedCriteria.indexOf(criteria),1);
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = message;
  }
}
