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



  private paymentUrl = bffURL + '/payment';


  pay(tableId: number){
    let paymentAccepted;
    let url = this.paymentUrl + "/" + tableId;
    this.http.post<Boolean>(url, paymentAccepted, {headers: new HttpHeaders(headerOptions)}).subscribe((paymentResult) => {
      paymentAccepted = paymentResult;
      if(!paymentAccepted) throw new Error('payment refused');
    });
  }
}
