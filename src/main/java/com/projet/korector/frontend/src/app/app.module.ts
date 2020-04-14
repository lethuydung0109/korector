import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SessionComponent } from './session/session.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';



import {NoPageFoundComponent} from './no-page-found/no-page-found.component';
import {SessionDetailComponent} from './session-detail/session-detail.component';
import {HomeComponent} from './home/home.component';
import {CreateSessionComponent} from './create-session/create-session.component';

import {StatsComponent} from './stats/stats.component';
import {AddStudentComponent} from './add-student/add-student.component';
import {AddTeacherComponent} from './add-teacher/add-teacher.component';
import {StudentStatsComponent} from './student-stats/student-stats.component';
import {ProfsStatsComponent} from './profs-stats/profs-stats.component';
import {ClassesStatsComponent} from './classes-stats/classes-stats.component';

import {NgxSpinnerModule} from 'ngx-spinner';


import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HttpClientModule} from '@angular/common/http';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {BoardAdminComponent} from './board-admin/board-admin.component';
import {BoardUserComponent} from './board-user/board-user.component';
import {BoardModeratorComponent} from './board-moderator/board-moderator.component';
import {ProfileComponent} from './profile/profile.component';
import {authInterceptorProviders} from './_helpers/auth.interceptor';
import {FormsModule} from '@angular/forms';
import {ProjectComponent} from './project/project.component';
import {SectionComponent} from './section/section.component';
import {SectionDetailComponent} from './section-details/section-detail.component';
import {CreateProjetComponent} from './createProjet/createProjet.component';
import {ProjetDetailComponent} from './projet-details/projet-detail.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import {CreateSectionComponent} from './createSection/createSection.component';

@NgModule({
  declarations: [
    AppComponent,
    SessionComponent,
    NavComponent,
    FooterComponent,
    NoPageFoundComponent,
    SessionDetailComponent,
    CreateSessionComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    BoardAdminComponent,
    BoardUserComponent,
    BoardModeratorComponent,
    ProfileComponent,
    StatsComponent,
    AddStudentComponent,
    AddTeacherComponent,
    StudentStatsComponent,
    ProfsStatsComponent,
    ClassesStatsComponent,
    ProjectComponent,
    SectionComponent,
    SectionDetailComponent,
    CreateProjetComponent,
    ProjetDetailComponent,
    CreateSectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    NgxSpinnerModule, /* for using spinner */
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule
   ],
  providers: [
    authInterceptorProviders,
    MatDatepickerModule,
    MatCardModule
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})

export class AppModule { }
