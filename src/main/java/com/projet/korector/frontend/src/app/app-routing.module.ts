import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {CriteriaListComponent} from "./criteria-list/criteria-list.component";
import {CreateCriteriaComponent} from "./create-criteria/create-criteria.component";
import {SearchCriteriaComponent} from "./search-criteria/search-criteria.component";

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  {path: 'criteria', component: CriteriaListComponent},
  {path: 'add', component: CreateCriteriaComponent},
  {path: 'findCriteria', component: SearchCriteriaComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
