import {Component, OnInit} from '@angular/core';
import {TableService} from "../../services/table.service";
import {Colors, Table} from "../../models/table.model";

@Component({
  selector: 'app-table-list',
  templateUrl: './table-list.component.html',
  styleUrls: ['./table-list.component.css']
})
export class TableListComponent implements OnInit {
  public tables: Table[] = [];

  constructor(private tableService: TableService) {
    this.tableService.tables$.subscribe((tables: Table[]) => {
      this.tables = tables;
    });
  }

  ngOnInit(): void {
  }

  onTableCLick(table : Table): void {
    /*
    for(let i = 0; i<this.tables.length; i++) {
      let table: Table = {number: this.tables[i].number +1, color: Colors.GRAY, taken: this.tables[i].taken, tableOrderId: this.tables[i].tableOrderId};
      this.tables[i] = table;
    }
     */
  }

}
