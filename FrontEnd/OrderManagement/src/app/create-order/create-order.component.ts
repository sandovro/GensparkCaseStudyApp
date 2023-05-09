import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-create-order',
  templateUrl: './create-order.component.html',
  styleUrls: ['./create-order.component.css']
})
export class CreateOrderComponent {
  storedEmail = localStorage.getItem("email")
  public orderForm!: FormGroup

  constructor(private orderService: OrderService){}

  ngOnInit(){
    this.createOrder();
  }

  public createOrder(): void {
    
  }

}
