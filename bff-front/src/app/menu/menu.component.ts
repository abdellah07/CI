import {Component, Input, OnInit, Output} from '@angular/core';
import {Category, Menu} from "../../models/menu.model";
import {Item, Order} from "../../models/order.model";
import {OrderService} from "../../services/order.service";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  @Input()
  menu: Menu = {id: "8167bc43-063e-49d3-97ba-9d7aff3a36bf", shortName: "foie gras", category: Category.STARTER};

  @Input()
  qte: number = 0;

  item: Item = {
    id: this.menu.id,
    shortName: this.menu.shortName,
    howMany: 0
  }



  constructor(private orderService: OrderService) {
    orderService.itemList$.subscribe(value =>{
      this.qte = orderService.getItemById(this.item.id).howMany;
    })
  }

  ngOnInit(): void {
    this.item = {
      id: this.menu.id,
      shortName: this.menu.shortName,
      howMany: 0
    }
  }

  onClick() {
    this.qte++;
    this.orderService.addItem(this.item);
  }
}
