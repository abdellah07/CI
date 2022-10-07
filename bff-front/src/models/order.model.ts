export class Item {
  id: String = "";
  shortName: String = "";
  howMany: number = 0;
}

export interface Order {
  tableId:number;
  items:Item[];
}

export interface preparedItems{
  "id": String,
  "shortName": String
}

export interface Preparation{
  "id": String,
  "shouldBeReadyAt": String,
  "preparedItems": preparedItems[]
}
