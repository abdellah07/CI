

export interface Table {
    number: number;
    taken: boolean;
    tableOrderId: string;
    color?:String;
}

export enum Colors {
  GREEN = "green",
  RED = "red",
  YELLOW = "yellow",
  BLUE = "blue"
}
