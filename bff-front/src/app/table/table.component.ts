import {Component, Input, OnChanges, OnInit, SimpleChanges, ChangeDetectionStrategy } from '@angular/core';
import {Colors, Table} from "../../models/table.model";

@Component({
  selector: 'app-table',
  templateUrl: './table.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./table.component.css']
})
export class TableComponent implements OnInit, OnChanges {

  @Input()
  table: Table = {number:-1,taken:false,tableOrderId:'undefined', color:Colors.BLACK};

  color: String = "gray";

  tableText: String = "";

  ngOnInit(): void {
  }

  ngOnChanges(changes: SimpleChanges) {
    let table: Table = changes['table'].currentValue;
    this.tableText = "Table #" + table.number;
    this.onColorChange(table.color)
  }

  onColorChange(color: Colors): void {
    this.color = color.valueOf();
  }


}
