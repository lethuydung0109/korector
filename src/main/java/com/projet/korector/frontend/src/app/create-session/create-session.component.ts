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
import { CriteriaService } from '../_services/criteria.service';
import { Router } from '@angular/router';


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

  nameSession: string;
  nameCritere : string;
  valueCritere: number;

  constructor(private router :Router,private sessionService : SessionService, private projectService : ProjectService, private criteriaService : CriteriaService) {
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
    console.log("critere :", listDynamicCriteria)
    this.dynamiqueCiterias=listDynamicCriteria;

  }

  public createSession(): void
  {
    let nameSession : string = document.getElementsByName("nameSession")[0]["value"];
    let dateDepot:string="";
    if(this.typeSession=="depot") dateDepot  = document.getElementsByName("date")[0]["value"];

    //var someString: string = "your JSON String here";
    //let jsonDate : any = JSON.parse(dateDepot);

    let createSession = new Session(nameSession, dateDepot);
    createSession.projects=this.selectedProjects;
    createSession.criterias=this.selectedCriteria;
    console.log("session à créer  : ", createSession);

    if(this.selectedCriteria.length == 0)
    {
      alert("Aucun critère sélectionné. Impossible de créer une session");
    }
    else{
      this.sessionService.createSession(createSession).subscribe(data =>{});
      this.router.navigate(['session']);
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

  changeNameCritere(name : string) : void
  {
    this.nameCritere=name;
  }

  addCriteria() : void
  {
    let criteria : Criteria;
    if(this.typeCritere=="static")
    {
      criteria = this.statiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;
      this.addCriteriaToSelectedCriteria(criteria);
    }
    else if (this.typeCritere=="dynamic")
    {
      criteria = this.dynamiqueCiterias.filter(critere => critere.name==this.nameCritere)[0];
      criteria.value=this.valueCritere;
      console.log("criteria",criteria);
      this.addCriteriaToSelectedCriteria(criteria);
    }
  }

  public changeSessionType(type : string) : void
  {
    this.typeSession=type;
  }

  public changeCritereType(type : string) : void
  {
    this.typeCritere=type;
  }

  public retrieveProjectToselected(project : Project) : void
  {
    this.selectedProjects.splice(this.selectedProjects.indexOf(project),1);
  }

  public retrieveCriteriaToSelected(criteria : Criteria) : void
  {
    this.selectedCriteria.splice(this.selectedCriteria.indexOf(criteria),1);
  }

  public addRow() : void
  {
    var tab:any = document.getElementById('tabCriteria');
    //var row:any=document.getElementById('rowCriteria');
    var tr = document.createElement('tr');
    tab.appendChild(tr);
    //tr.appendChild(row);
    var tdName = document.createElement('td');
    var tdValue = document.createElement('td');
    var tdInput1=document.createElement('input');
    var tdInput2=document.createElement('input');
    tdName.appendChild(tdInput1);
    tdValue.appendChild(tdInput2);
    tr.appendChild(tdName);
    tr.appendChild(tdValue);


  }
}
