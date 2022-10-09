export interface ItemInfo {
  id: String;
  shortName: String;
  takenForService: boolean;
}

export interface OrderInfo {
  ready: ItemInfo[];
  served: ItemInfo[];
  unready: ItemInfo[];
}
