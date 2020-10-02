import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ProductService } from 'src/app/services/product.service';
import { HttpClient } from '@angular/common/http';
import { CustomResponse } from 'src/app/models/CustomResponse';
import { ProductOrder } from 'src/app/models/ProductOrder';

@Component({
  selector: 'app-update-an-order',
  templateUrl: './update-an-order.component.html',
  styleUrls: ['./update-an-order.component.css']
})
export class UpdateAnOrderComponent implements OnInit {
  @ViewChild('f', { static: false }) updateForm: NgForm;
  submitted = false;
  productOrders:ProductOrder[]
  order:ProductOrder;
  constructor(private http: HttpClient,private productService: ProductService) { 
   
    this.productOrders=this.productService.getProductOrders();
   
  }

  orderId:number;
  orderStatus:string;
  response:string;
  readonly baseUrl = 'http://localhost:1119/productOrder';
  updateOrder(orderId:Number,orderStatus:String):string{
    this.http.get<string>(this.baseUrl+"/updateOrder/"+orderId+"/"+orderStatus).subscribe((res)=>{
      console.log(res);
      this.response=res;
    });
    return this.response;
  }
  ngOnInit(): void {
  }
  onSubmit() {
    this.orderId=this.order.orderId;
    this.response=this.updateOrder(this.orderId,this.orderStatus);
    console.log("jkj" + this.response)
    this.submitted = true;
    console.log(this.response);
    this.updateForm.reset();
  }

}
