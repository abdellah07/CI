import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { BehaviorSubject, Subject } from 'rxjs';
import {diningUrl, headerDict} from '../configs/server.config';
import {TABLES_LIST} from "../mocks/table-list.mock";
import {Table} from "../models/table.model";

@Injectable({
  providedIn: 'root',
})
export class TableService {

  private tables: Table[] = TABLES_LIST;

  public tables$: BehaviorSubject<Table[]> = new BehaviorSubject(this.tables);

  private tablesUrl = diningUrl + '/tables';


  constructor(private http: HttpClient) {
    this.retrieveTables();
  }

  retrieveTables(): void {
    this.http.get<Table[]>(this.tablesUrl,{headers: new HttpHeaders(headerDict)}).subscribe((quizList) => {
      this.tables = quizList;
      this.tables$.next(this.tables);
    });
  }
}
