import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TableListComponent } from './table-list/table-list.component';
import {MenuListComponent} from "./menu-list/menu-list.component";
import {OrderInfoComponent} from "./order-info/order-info.component";

const routes: Routes = [
  {
    path: 'menu-list/:tableId',
    component: MenuListComponent,
  },
  {
    path: 'table-list',
    component: TableListComponent,
  },
  {
    path: 'order-info/:tableId',
    component: OrderInfoComponent,
  },
  {
    path: '',
    redirectTo: 'table-list',
    pathMatch: 'full',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
