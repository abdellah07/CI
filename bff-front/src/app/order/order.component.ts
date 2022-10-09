import { Component, OnInit } from '@angular/core';
import {OrderService} from "../../services/order.service";
import {Item} from "../../models/order.model";
import {Menu} from "../../models/menu.model";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  items : Item[] = [];

  constructor(private orderService:OrderService) {
    this.orderService.itemList$.subscribe((items: Item[]) => {
      this.items = items;
    });
  }

  ngOnInit(): void {
  }

  remove(id:String){
    this.orderService.removeItem(id);
    console.log(this.items)
  }

  validate(): void{
    this.orderService.sendOrderToBff();
  }

}
