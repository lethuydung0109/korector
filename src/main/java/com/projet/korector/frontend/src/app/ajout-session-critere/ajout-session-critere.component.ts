import { Component, OnInit } from '@angular/core';
import { Criteria } from '../classes/criteria';
import { SessionCritere } from '../classes/session-critere';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { SessionService } from '../_services/session.service';
import { CriteriaService } from '../_services/criteria.service';
import { SessionCritereService } from '../_services/session-critere.service';
import { ValidationModalComponent } from '../validation-modal/validation-modal.component';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { TokenStorageService } from '../_services/token-storage.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-ajout-session-critere',
  templateUrl: './ajout-session-critere.component.html',
  styleUrls: ['./ajout-session-critere.component.scss']
})
export class AjoutSessionCritereComponent implements OnInit {

  public statiqueCiterias : Array<Criteria> =[];
  public dynamiqueCiterias : Array<Criteria> =[];
  public createdCriteria : Array<SessionCritere> = [];
  public typeSession : string;
  public typeCritere : string;
  public listStaticCriteria: Array<Criteria> =[];

  public listSessionCritere : Array<SessionCritere> = [];

  public sessionId: number;
  public nameSession: string;
  public nameCritere : string;
  public poidsCritere: number;
  public seuilCritere: number;
  public heureDepot : string;
  public userRole: string;

  constructor(
    private actRoute: ActivatedRoute, 
    private router :Router,
    private modalService: NgbModal,
    private sessionService : SessionService, 
    private criteriaService : CriteriaService,
    private sessionCritereService : SessionCritereService,
    private http: HttpClient, 
    private tokenStorage: TokenStorageService
  ) { 
    this.typeCritere="Statique";
    this.sessionId = this.actRoute.snapshot.params.id;
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
      
    let listStaticCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Statique').subscribe(data => {
      data.forEach(c => {
        listStaticCriteria.push(c);
      })
    });
    console.log("criteres statiques : ", listStaticCriteria)
    this.statiqueCiterias=listStaticCriteria;

    let listDynamicCriteria: Array<Criteria>=[];
    this.criteriaService.searchCriteriaByType('Dynamique').subscribe(data => {
      data.forEach(c => {
        listDynamicCriteria.push(c);
      })
    });
    console.log("criteres dynamiques :", listDynamicCriteria);
    this.dynamiqueCiterias=listDynamicCriteria;
  }

  public updateSession()
  {
    let sessionCritereIds : Array<number>=[];

    let pourcentageTotal=0;
    this.createdCriteria.forEach(sc=> { 
      sessionCritereIds.push(sc.id); 
      pourcentageTotal=pourcentageTotal+sc.height;
    });

    if(pourcentageTotal===100)
    {
      this.sessionService.getSessionById(this.sessionId).subscribe(
        data => {
          data.sessionCritere=sessionCritereIds;
        }
      );
      this.router.navigate(['/session']);
    }
    else this.openValidationModal("Le poids total des critères n'est pas égal à 100");    
  }
 
  public changeNameCritere(name : string) : void { this.nameCritere=name; }

  public changeCritereType(type : string): void { this.typeCritere=type; }

  public addCriteriaToSelectedCriteria(c : SessionCritere) : void {
    if(!this.createdCriteria.includes(c)) this.createdCriteria.push(c);
    else this.openValidationModal("Ce critère est déjà dans la liste");
  }

  public addCriteria() : void
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
      }
      
      this.sessionCritereService.createSessionCritere(sessionCritere).subscribe(data=>{
        console.log(data)
        this.addCriteriaToSelectedCriteria(data);
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
      }

      this.sessionCritereService.createSessionCritere(sessionCritere).subscribe(data=>{
        this.addCriteriaToSelectedCriteria(data);
      });
    }
  }

  public retrieveCriteriaToSelected(sessionCritere : SessionCritere) : void
  {
    this.sessionCritereService.deleteSessionCritere(this.sessionId,sessionCritere.id).subscribe(data=>{});
    this.createdCriteria.splice(this.createdCriteria.indexOf(sessionCritere),1);
  }

  public openValidationModal(message:string) : void {
    const modalRef = this.modalService.open(ValidationModalComponent);
    modalRef.componentInstance.message = message;
  }

}
