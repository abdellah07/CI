import {ChangeDetectionStrategy, Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {getColor, Table, TableStatus} from "../../models/table.model";
import {OrderService} from "../../services/order.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnChanges {

  @Input()
  table: Table = {number: -1, taken: false, tableOrderId: 'undefined', status: TableStatus.noInfo};

  color: String = "gray";

  tableText: String = "";

  constructor(private orderService: OrderService, private router: Router) {
  }

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges) {
    let table: Table = changes['table'].currentValue;
    this.tableText = "Table #" + table.number;
    this.onStatusChange(table.status)
  }

  onStatusChange(status: TableStatus): void {
    this.color = getColor(status).valueOf();
  }

  onClick() {
    console.log("test")
    this.orderService.tableId = this.table.number;
    switch (this.table.status){
      case TableStatus.payed:
        this.table.status = TableStatus.available;
        this.onStatusChange(this.table.status);
        break;
      default:
        this.router.navigate(["/menu-list/"+this.table.number]);
        break;
    }
  }


}
