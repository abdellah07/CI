

export interface Table {
    number: number;
    taken: boolean;
    tableOrderId: string;
    status: TableStatus;
}

export interface TableInfo {
  available: Table[];
  waitingForPayment: Table[];
  payed: Table[];
  inPreparation: Table[];
  orderReady: Table[];
  noInfo: Table[];
}

export enum Colors {
  GREEN = "green",
  RED = "red",
  YELLOW = "yellow",
  BLUE = "blue",
  VIOLET = "violet",
  GRAY = "gray"
}

export enum TableStatus {
  available,
  waitingForPayment,
  payed,
  inPreparation,
  orderReady ,
  noInfo
}

export function getColor(stat : TableStatus): Colors{
  switch (stat){
    case TableStatus.available:
      return Colors.GREEN;
    case TableStatus.waitingForPayment:
      return Colors.RED;
    case TableStatus.payed:
      return Colors.VIOLET;
    case TableStatus.inPreparation:
      return Colors.YELLOW;
    case TableStatus.orderReady:
      return Colors.BLUE;
    case TableStatus.noInfo:
      return Colors.GRAY;
    default:
      return Colors.GRAY;
  }
}
