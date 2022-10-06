

export interface Table {
    number: number;
    taken: boolean;
    tableOrderId: string;
    color: Colors;
}

export enum Colors {
  GREEN = "green",
  RED = "red",
  YELLOW = "yellow",
  BLUE = "blue",
  BLACK = "violet",
  GRAY = "gray"
}
