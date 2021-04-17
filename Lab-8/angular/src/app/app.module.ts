import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { NameSearchComponent } from './name-search/name-search.component';
import { RoleSearchComponent } from './role-search/role-search.component';
import { HomeComponent } from './home/home.component';
import { AddComponent } from './add/add.component';
import { RemoveComponent } from './remove/remove.component';
import { UpdateComponent } from './update/update.component';
import { HttpClientModule } from '@angular/common/http';
import { UserListComponent } from './user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    NameSearchComponent,
    RoleSearchComponent,
    HomeComponent,
    AddComponent,
    RemoveComponent,
    UpdateComponent,
    UserListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
