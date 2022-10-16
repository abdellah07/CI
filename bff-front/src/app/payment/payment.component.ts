import { Component, OnInit } from '@angular/core';
import {PaymentService} from "../../services/payment.service";
import {Item} from "../../models/order.model";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  public itemList: Item[] = [];
  public tableId: number = 0;
  public total: number= 0;

  constructor(private paymentService: PaymentService, private route: ActivatedRoute, private router: Router) {
    this.paymentService.itemList$.subscribe((item: Item[]) => {
      this.itemList = item;
      this.total = 0;
      for(const item of this.itemList){
        if(item) {
          if(item.price) this.total += item.price;
        }
      }
    });
  }



  ngOnInit(): void {
    let idT: string | null = this.route.snapshot.paramMap.get('tableId');
    if (idT == null){
      this.tableId = 0;
      this.router.navigate(["/table-list/"]);
    }
    else
      this.tableId = parseInt(idT);
    this.paymentService.getOrderList(this.tableId)
  }

  pay(): void {
    this.paymentService.pay(this.tableId);
    this.router.navigate(["/table-list/"]);
  }

}
