import {Component, OnInit} from '@angular/core';
import {OrderInfo} from "../../models/orderinfo.model";
import {OrderInfoService} from "../../services/order-info.service";
import {orderInfoList} from "../../mocks/orderinfo.mock";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-info',
  templateUrl: './order-info.component.html',
  styleUrls: ['./order-info.component.css']
})
export class OrderInfoComponent implements OnInit {

  orderInfo: OrderInfo = orderInfoList;
  tableId: number = 0;

  constructor(private orderInfoService: OrderInfoService, private route: ActivatedRoute, private router: Router) {
    this.orderInfoService.orderInfo$.subscribe((orderInfo: OrderInfo) => {
      this.orderInfo = orderInfo;
    });
  }

  ngOnInit(): void {
    let idT: string | null = this.route.snapshot.paramMap.get('tableId');
    if (idT == null)
      this.tableId = 0;
    else
      this.tableId = parseInt(idT);
    this.orderInfoService.retrieveOrderInfoList(this.tableId);
  }

  onclick(){
    this.router.navigate(["/menu-list/"+this.tableId]);
  }
}
