import {Component, OnInit} from '@angular/core';
import {OrderInfo} from "../../models/orderinfo.model";
import {OrderInfoService} from "../../services/order-info.service";
import {orderInfoList} from "../../mocks/orderinfo.mock";
import {ActivatedRoute, Router} from "@angular/router";
import {OrderService} from "../../services/order.service";
import {PaymentService} from "../../services/payment.service";
import {TableInfo} from "../../models/table.model";

@Component({
  selector: 'app-order-info',
  templateUrl: './order-info.component.html',
  styleUrls: ['./order-info.component.css']
})
export class OrderInfoComponent implements OnInit {

  orderInfo: OrderInfo = orderInfoList;
  tableId: number = 0;


  constructor(private orderService: OrderService, private paymentServer: PaymentService, private orderInfoService: OrderInfoService, private route: ActivatedRoute, private router: Router) {
    this.orderInfoService.orderInfo$.subscribe((orderInfo: OrderInfo) => {
      this.orderInfo = orderInfo;
    });
  }

  ngOnInit(): void {
    this.orderInfoService.retrieveOrderInfoList();
  }

  onAddClick() {
    this.router.navigate(["/game"]);
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
