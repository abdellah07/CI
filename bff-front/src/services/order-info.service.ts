import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {headerOptions, orderinfoUrl} from '../configs/server.config';
import {OrderService} from "./order.service";
import {OrderInfo} from "../models/orderinfo.model";
import {orderInfoList} from "../mocks/orderinfo.mock";

@Injectable({
  providedIn: 'root',
})
export class MenuService {

  private orderInfos: OrderInfo = orderInfoList;

  public orderInfo$: BehaviorSubject<OrderInfo> = new BehaviorSubject(this.orderInfos);

  constructor(private http: HttpClient) {
  }

  retrieveOrderInfoList(tableId: number) {
    let url = orderinfoUrl + "/" + tableId;
    this.http.get<OrderInfo>(url, {headers: new HttpHeaders(headerOptions)}).subscribe((orderInfos) => {
      this.orderInfos = orderInfos;
      this.orderInfo$.next(this.orderInfos);
    });
  }
}
