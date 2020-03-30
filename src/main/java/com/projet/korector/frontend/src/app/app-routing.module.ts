import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SessionComponent } from './session/session.component';
import { HomeComponent } from './home/home.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { SessionDetailComponent } from './session-detail/session-detail.component';
import { CreateSessionComponent } from './create-session/create-session.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';

//{ Awa part }
import { AddComponent } from './add/add.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddTeacherComponent } from './add-teacher/add-teacher.component';


const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  //{ path: 'about', component: AboutComponent },
  { path: 'session', component: SessionComponent },
  { path: 'session-detail/:id', component: SessionDetailComponent },
  { path: 'createSession', component: CreateSessionComponent },
  //{ path: '**', component: NoPageFoundComponent }
  // Dung's login part
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'admin', component: BoardAdminComponent },
  //
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  //{ Awa part}
  { path: 'add', component: AddComponent },
  { path: 'addStudent', component: AddStudentComponent },
  { path: 'addTeacher', component: AddTeacherComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
