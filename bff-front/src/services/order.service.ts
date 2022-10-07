import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject, catchError, Observable, throwError} from 'rxjs';
import { headerOptions, orderUrl} from '../configs/server.config';

import {Item, Order, Preparation} from "../models/order.model";
import {MENU_LIST} from "../mocks/menu-list.mock";
import {Category, Menu} from "../models/menu.model";
import {logMessages} from "@angular-devkit/build-angular/src/builders/browser-esbuild/esbuild";
import {Table} from "../models/table.model";

@Injectable({
  providedIn: 'root',
})
export class OrderService {

  constructor(private http: HttpClient) {
  }

  tableId :number = -1

  itemList : Item[] = [];


  public itemList$: BehaviorSubject<Item[]> = new BehaviorSubject(this.itemList);

  generateAnOrder(tableId: number) {
    this.tableId = tableId;
  }

  setTable(id : number){
    console.log("table id is changed to "+id)
    this.tableId = id;
    this.itemList = [];
    this.itemList$.next(this.itemList);
  }

  addItem(item: Item) {
    let i: Item | undefined;
    i = this.itemList.find(it => it.id == item.id);
    if (i == undefined) {
      item.howMany = 1;
      this.itemList.push(item);
    } else {
      i.howMany++;
    }
    this.itemList$.next(this.itemList);
  }

  removeItem(id: String) {
    let i: Item | undefined;
    i = this.itemList.find(it => it.id == id);
    if (i != undefined) {
      i.howMany--;
      if(i.howMany <=0){
        this.itemList = this.itemList.filter(value => value.id !== id);
      }
    }
    this.itemList$.next(this.itemList);
  }

  private handleError(error: HttpErrorResponse) {
    if (error.status === 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occurred:', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(
        `Backend returned code ${error.status}, body was: `, error.error);
    }
    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }

  getItemById(id:String):Item{
    let i =  this.itemList.find(it => it.id == id);
    if (i != undefined) {
      return i
    }
    return {id: "null",
      shortName: "null",
      howMany: 0};
  }

  sendOrderToBff() {
    this.showOrder();
    let url = orderUrl+"/"+this.tableId;
    this.http.post<Item[]>(url,this.itemList,{headers: new HttpHeaders(headerOptions)}).subscribe((item) => {
      this.itemList = item;
      this.itemList$.next(this.itemList);
    });

  }

  getOrder() {
    let order : Order = {tableId:this.tableId,items: this.itemList}
    return this.itemList;
  }

  showOrder() {
    console.log(this.itemList);
  }


}
