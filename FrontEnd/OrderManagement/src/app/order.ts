import { Item } from "./item";


export interface Order{
    orderId: number;
    orderTotal: number;
    items: Item[];
}