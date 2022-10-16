import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Item} from "../models/order.model";
import {BehaviorSubject} from "rxjs";
import {bffURL, headerOptions, orderUrl} from "../configs/server.config";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) {
  }

  itemList: Item[] = [];

  public itemList$: BehaviorSubject<Item[]> = new BehaviorSubject(this.itemList);

  private paymentUrl = bffURL + '/payment';
  private url = bffURL + '/order';

  pay(tableId: number){
    let paymentAccepted;
    let url = this.paymentUrl + "/" + tableId;
    this.http.post<Boolean>(url, paymentAccepted, {headers: new HttpHeaders(headerOptions)}).subscribe((paymentResult) => {
      paymentAccepted = paymentResult;
      if(!paymentAccepted) throw new Error('payment refused');
    });
  }

  getOrderList(tableId: number){
    let url = this.url + "/" + tableId;
    this.http.get<Item[]>(url,{headers: new HttpHeaders(headerOptions)}).subscribe((paymentResult) => {
      this.itemList = paymentResult;
      this.itemList$.next(this.itemList);
    });
  }

}
