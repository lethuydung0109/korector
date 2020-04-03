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
//import { StatsComponent } from './stats/stats.component';
import { AddStudentComponent } from './add-student/add-student.component';
import { AddTeacherComponent } from './add-teacher/add-teacher.component';
// import { StudentStatsComponent } from './student-stats/student-stats.component';
// import { ProfsStatsComponent } from './profs-stats/profs-stats.component';
// import { ClassesStatsComponent } from './classes-stats/classes-stats.component';


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
  //{ path: 'add', component: StatsComponent },
  { path: 'addStudent', component: AddStudentComponent },
  { path: 'addTeacher', component: AddTeacherComponent },
  // { path: 'stats', component: StatsComponent },
  // { path: 'studentStats', component: StudentStatsComponent },
  // { path: 'profsStats', component: ProfsStatsComponent},
  // { path: 'classesStats', component: ClassesStatsComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
