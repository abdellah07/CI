export enum Category {
STARTER,
MAIN,
DESSERT,
BEVERAGE
}


export interface Menu {
  id: String;
  shortName: String;
  category: Category;
}
