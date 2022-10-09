import {Component, Input, OnInit} from '@angular/core';
import {MenuService} from "../../services/menu.service";
import {Category, Menu} from "../../models/menu.model";
import {OrderService} from "../../services/order.service";
import {MENU_LIST} from "../../mocks/menu-list.mock";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-menu-list',
  templateUrl: './menu-list.component.html',
  styleUrls: ['./menu-list.component.css']
})
export class MenuListComponent implements OnInit {

  tableId: number = 0;

  menuList: Menu[] = []
  active: boolean = true;

  constructor(private menuService: MenuService, private orderService: OrderService, private route: ActivatedRoute) {
    this.menuService.menuList$.subscribe((menus: Menu[]) => {
      this.menuList = menus;
    });
  }

  ngOnInit(): void {
    let idT : string | null = this.route.snapshot.paramMap.get('tableId');
    if (idT == null)
      this.tableId = 0;
    else
      this.tableId = parseInt(idT);

    this.orderService.setTable(this.tableId)
  }

  toggle() {
    const button = document.getElementById("order");
    if (button != null) {
      if (this.active) {
        button.style.display = "block";
      } else {
        button.style.display = "none";
      }
      this.active = !this.active
    }
  }

}
