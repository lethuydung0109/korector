import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SessionComponent } from './session/session.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { NoPageFoundComponent } from './no-page-found/no-page-found.component';
import { SessionDetailComponent } from './session-detail/session-detail.component';
import { HomeComponent } from './home/home.component';
import { CreateSessionComponent } from './create-session/create-session.component';

@NgModule({
  declarations: [
    AppComponent,
    SessionComponent,
    NavComponent,
    FooterComponent,
    NoPageFoundComponent,
    SessionDetailComponent,
    HomeComponent,
    CreateSessionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
