import{BrowserModule}from'@angular/platform-browser';
import {NgModule }from '@angular/core';
import {FormsModule}from '@angular/forms';

import {AppComponent}from './app.component';
import { UsersComponent}from './users/users.component';
import { UserDetailComponent } from './user-detail/user-detail.component';


@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
