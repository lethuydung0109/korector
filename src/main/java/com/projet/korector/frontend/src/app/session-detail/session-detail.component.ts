import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-session-detail',
  templateUrl: './session-detail.component.html',
  styleUrls: ['./session-detail.component.scss']
})
export class SessionDetailComponent implements OnInit {

  sessionId: string;

  constructor(private actRoute: ActivatedRoute) {
    this.sessionId = this.actRoute.snapshot.params.id;
  }

  ngOnInit(): void {
  }

}
