import { Component, OnInit } from '@angular/core';
import {OrderInfo} from "../../models/orderinfo.model";
import {orderInfoList} from "../../mocks/orderinfo.mock";
import {OrderService} from "../../services/order.service";
import {PaymentService} from "../../services/payment.service";
import {OrderInfoService} from "../../services/order-info.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit {

  orderInfo: OrderInfo = orderInfoList;
  tableId: number = 0;


  constructor(private orderService: OrderService, private paymentServer: PaymentService, private orderInfoService: OrderInfoService, private route: ActivatedRoute, private router: Router) {
    this.orderInfoService.orderInfo$.subscribe((orderInfo: OrderInfo) => {
      this.orderInfo = orderInfo;
    });
  }

  ngOnInit(): void {

  }

  onAddClick() {
    this.router.navigate(["/order-info"]);
  }

  onPaymentClick() {
    if(this.orderInfo.unready.length == 0 && this.orderInfo.ready.length == 0) {
      this.router.navigate(["/payment/" + this.tableId]);
    }
  }

  onServeClick() {
    this.orderService.serve(this.tableId);
    this.router.navigate([""]);
  }

}
