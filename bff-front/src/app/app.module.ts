import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TableComponent } from './table/table.component';
import {HttpClientModule} from "@angular/common/http";
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { TableListComponent } from './table-list/table-list.component';
import { MenuComponent } from './menu/menu.component';
import { MenuListComponent } from './menu-list/menu-list.component';
import { OrderComponent } from './order/order.component';
import { OrderInfoComponent } from './order-info/order-info.component';
import { PaymentComponent } from './payment/payment.component';
import { GameComponent } from './game/game.component';
import { ConfigComponent } from './config/config.component';
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    TableComponent,
    TableListComponent,
    MenuComponent,
    MenuListComponent,
    OrderComponent,
    OrderInfoComponent,
    PaymentComponent,
    GameComponent,
    ConfigComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        NgbModule,
        FormsModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
