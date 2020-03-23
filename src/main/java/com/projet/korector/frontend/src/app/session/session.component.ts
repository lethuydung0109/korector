import { Component, OnInit } from '@angular/core';
import { Session } from '../classes/session';
import { SessionService } from '../services/session.service';

@Component({
  selector: 'app-session',
  templateUrl: './session.component.html',
  styleUrls: ['./session.component.scss']
})

export class SessionComponent implements OnInit {

  constructor(private sessionService : SessionService) { }
  public sessions : Array<Session>;

  ngOnInit(): void {
    //initialiser liste
  }

}
