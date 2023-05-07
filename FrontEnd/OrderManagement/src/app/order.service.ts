import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
import { Order } from "./order";

@Injectable({
    providedIn: 'root'
})

export class OrderService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {}

    public getOrders(): Observable<Order[]> {
        return this.http.get<Order[]>(`${this.apiServerUrl}/user/order/${localStorage.getItem("email")}`)
    }

    public addOrder(order: Order): Observable<Order> {
        return this.http.post<Order>(`${this.apiServerUrl}/item/{userEmail}/{orderId}/{itemName}`, order)
    }

    public deleteOrder(orderId: number): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/${orderId}`)
    }



}