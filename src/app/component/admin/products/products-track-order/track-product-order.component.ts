import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { CustomResponse } from 'src/app/models/CustomResponse';
import { ProductOrder } from 'src/app/models/ProductOrder';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-track-product-order',
  templateUrl: './track-product-order.component.html',
  styleUrls: ['./track-product-order.component.css']
})
export class TrackProductOrderComponent implements OnInit {
  @ViewChild('f', { static: false }) signupForm: NgForm;
  productOrder : ProductOrder;
  trackResponse:string;
  submitted:boolean=false;
  orderId:number;
  productOrders:ProductOrder[]
  order:ProductOrder;
  readonly baseUrl = 'http://localhost:1119/productOrder';
  constructor(private http:HttpClient, private productService: ProductService) { 
   
    this.productOrders=this.productService.getProductOrders();
   
  }
  ngOnInit(): void {
  }

  trackProductOrder(Id:number): ProductOrder{
    this.http.get<ProductOrder>(this.baseUrl+"/trackOrder/"+Id).subscribe((res)=>{
      console.log(res);
     this.productOrder=res;
    });
    return this.productOrder;
   // return this.trackResponse;
  }

  onSubmit(){
    this.orderId=this.order.orderId;
  this.trackProductOrder(this.orderId);
    this.submitted=true;
    this.signupForm.reset();
  }
}
