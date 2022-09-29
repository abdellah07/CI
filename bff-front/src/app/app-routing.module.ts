import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TablesComponent} from "./tables/tables.component";

const routes: Routes = [
  {
    path: 'tables',
    component: TablesComponent,
  },
  {
    path: '',
    redirectTo: 'tables',
    pathMatch: 'full',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
