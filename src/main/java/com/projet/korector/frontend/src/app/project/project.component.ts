import {Component, OnInit} from '@angular/core';
import {Project} from '../classes/project';
import {ProjectService} from '../_services/project.service';
import {Observable} from 'rxjs';

import {Router} from '@angular/router';
import {TokenStorageService} from '../_services/token-storage.service';
import {User} from '../classes/user';
import {isEmpty} from 'rxjs/operators';

@Component({
  selector: 'app-projet',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.scss']
})

export class ProjectComponent implements OnInit {
  user: User;
  projets: Observable<Project[]>;
  empty = false;

  constructor(private projetService: ProjectService,
              private router: Router, private tokenStorage: TokenStorageService) {}

  ngOnInit() {
    this.user = this.tokenStorage.getUser();
    this.reloadData();
  }

  reloadData() {
    this.projets = this.projetService.getProject(this.user.id);

    this.projets.subscribe((value) => {
      console.log(value);
      // tslint:disable-next-line:triple-equals
      if(value.length == 0) {
        this.empty = true;
      }
    }, (error) => {
      console.log(error);
    }, () => {
      console.log('Fini !');
    });
}

  deleteProject(id: number) {
    this.projetService.deleteProject(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
    this.reloadData();
  }

  projetDetails(id: number) {
    this.router.navigate(['projet-detail', id]);
  }

  updateProject(id: number) {
    this.router.navigate(['projet-detail', id]);
  }
}
