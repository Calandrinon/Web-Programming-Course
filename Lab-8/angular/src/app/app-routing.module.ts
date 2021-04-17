import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddComponent } from './add/add.component';
import { HomeComponent } from './home/home.component';
import { NameSearchComponent } from './name-search/name-search.component';
import { RemoveComponent } from './remove/remove.component';
import { RoleSearchComponent } from './role-search/role-search.component';
import { UpdateComponent } from './update/update.component';

const routes: Routes = [
  {path: "home", component: HomeComponent},
  {path: "users", component: NameSearchComponent},
  {path: "roles", component: RoleSearchComponent},
  {path: "add", component: AddComponent},
  {path: "remove", component: RemoveComponent},
  {path: "update", component: UpdateComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
