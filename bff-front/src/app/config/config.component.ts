import {Component, OnInit} from '@angular/core';
import {OrderService} from "../../services/order.service";
import {OrderInfoService} from "../../services/order-info.service";
import {Router} from "@angular/router";
import {PaymentService} from "../../services/payment.service";

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.css']
})
export class ConfigComponent implements OnInit {
  tableId: number = 0;

  constructor(private paymentService: PaymentService,private orderInfoService: OrderInfoService, private router: Router) {

  }
  ngOnInit(): void {
  }

  onSubmit(){
    this.orderInfoService.tableId = this.tableId;
    this.paymentService.tableId = this.tableId;
    this.router.navigate(["/order-info"]);
  }
}
