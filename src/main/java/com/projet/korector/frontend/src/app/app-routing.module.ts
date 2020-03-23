import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SessionComponent } from './session/session.component';
import { HomeComponent } from './home/home.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { SessionDetailComponent } from './session-detail/session-detail.component';
import { CreateSessionComponent } from './create-session/create-session.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  //{ path: 'about', component: AboutComponent },
  { path: 'session', component: SessionComponent },
  { path: 'session-detail/:id', component: SessionDetailComponent },
  { path: 'createSession', component: CreateSessionComponent },
  //{ path: '**', component: NoPageFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
