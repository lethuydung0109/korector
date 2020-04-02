import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Project } from '../classes/project';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.scss']
})
export class SessionDetailComponent implements OnInit {

  public sessionId: Number;
  public projects : Array<Project>;

  constructor(private actRoute: ActivatedRoute, private sessionService : SessionService) {
    this.sessionId = this.actRoute.snapshot.params.id;
  }

  ngOnInit(): void {


     let listProjects : Array<Project> =[];
     this.sessionService.getSessionProjects(this.sessionId).subscribe(data => {
       data.forEach(p => {
        listProjects.push(p);
       })
     });
     this.projects=listProjects;
  }

}
