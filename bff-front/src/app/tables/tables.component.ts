import { Component, OnInit } from '@angular/core';
import {Table} from "../../models/table.model";
import {TableService} from "../../services/table.service";

@Component({
  selector: 'app-tables',
  templateUrl: './tables.component.html',
  styleUrls: ['./tables.component.css']
})
export class TablesComponent implements OnInit {

  public tables: Table[] = [];

  constructor(private tableService: TableService) {
    this.tableService.tables$.subscribe((tables: Table[]) => {
      this.tables = tables;
    });
  }

  ngOnInit(): void {
  }

}
