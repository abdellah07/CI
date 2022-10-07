import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject } from 'rxjs';
import {headerOptions, menuUrl} from '../configs/server.config';
import {MENU_LIST} from "../mocks/menu-list.mock";
import {Menu} from "../models/menu.model";
import {OrderService} from "./order.service";
import {Item} from "../models/order.model";

@Injectable({
  providedIn: 'root',
})
export class MenuService {

  private menuList: Menu[] = MENU_LIST;

  public menuList$: BehaviorSubject<Menu[]> = new BehaviorSubject(this.menuList);

  constructor(private http: HttpClient, private orderService:OrderService) {
    this.retrieveMenuList();
  }

  retrieveMenuList(): void {
    this.http.get<Menu[]>(menuUrl,{headers: new HttpHeaders(headerOptions)}).subscribe((menuList) => {
      this.menuList = menuList;
      this.menuList$.next(this.menuList);
    });
  }
}
