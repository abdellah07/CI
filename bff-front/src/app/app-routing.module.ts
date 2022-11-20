import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TableListComponent } from './table-list/table-list.component';
import {MenuListComponent} from "./menu-list/menu-list.component";
import {OrderInfoComponent} from "./order-info/order-info.component";
import {PaymentComponent} from "./payment/payment.component";
import {GameComponent} from "./game/game.component";
import {ConfigComponent} from "./config/config.component";

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
    path: 'payment/:tableId',
    component: PaymentComponent
  },
  {
    path: 'order-info',
    component: OrderInfoComponent,
  },
  {
    path: 'game',
    component: GameComponent,
  },
  {
    path: 'config',
    component: ConfigComponent,
  },
  {
    path: '',
    redirectTo: 'config',
    pathMatch: 'full',
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
