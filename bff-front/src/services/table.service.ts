import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {BehaviorSubject} from 'rxjs';
import {bffURL, headerOptions} from '../configs/server.config';
import {TABLES_LIST} from "../mocks/table-list.mock";
import {Table, TableInfo, TableStatus} from "../models/table.model";

@Injectable({
  providedIn: 'root',
})
export class TableService {

  private tables: Table[] = TABLES_LIST;

  public tables$: BehaviorSubject<Table[]> = new BehaviorSubject(this.tables);

  private tablesUrl = bffURL + '/tables';


  constructor(private http: HttpClient) {
    this.retrieveTables();
  }

  tableInfoToTable(tableInfo: TableInfo): Table[]{
    let tables: Table[] = [];

    for(let tableIn of tableInfo.available){
      let table : Table = tableIn;
      table.status = TableStatus.available;
      tables.push(table);
    }

    for(let tableIn of tableInfo.inPreparation){
      let table : Table = tableIn;
      table.status = TableStatus.inPreparation;
      tables.push(table);
    }

    for(let tableIn of tableInfo.payed){
      let table : Table = tableIn;
      table.status = TableStatus.payed;
      tables.push(table);
    }

    for(let tableIn of tableInfo.orderReady){
      let table : Table = tableIn;
      table.status = TableStatus.orderReady;
      tables.push(table);
    }

    for(let tableIn of tableInfo.waitingForPayment){
      let table : Table = tableIn;
      table.status = TableStatus.waitingForPayment;
      tables.push(table);
    }

    for(let tableIn of tableInfo.noInfo){
      let table : Table = tableIn;
      table.status = TableStatus.noInfo;
      tables.push(table);
    }

    return tables;
  }
  retrieveTables(): void {
    this.http.get<TableInfo>(this.tablesUrl,{headers: new HttpHeaders(headerOptions)}).subscribe((table) => {
      this.tables = this.tableInfoToTable(table);
      this.tables$.next(this.tables);
    });
  }
}
