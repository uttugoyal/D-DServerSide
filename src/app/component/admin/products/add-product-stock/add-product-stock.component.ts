import { Component, OnInit, ViewChild } from '@angular/core';
import { ProductStock } from 'src/app/models/ProductStock';
import { ProductService } from 'src/app/services/product.service';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-add-product-stock',
  templateUrl: './add-product-stock.component.html',
  styleUrls: ['./add-product-stock.component.css']
})
export class AddProductStockComponent implements OnInit {
  @ViewChild('f', { static: false }) orderForm: NgForm;
  names = ['a', 'b'];
  stock: ProductStock = new ProductStock();
  stockId: Number;
  name: string;
  qualityCheck: string;
  quantityUnit: number;
  pricePerUnit: number;
  manufacturingDate: Date;
  expiryDate: Date;
  submitted = false;

  constructor(private productService: ProductService) {
  
   }

  ngOnInit(): void {
  }

  postStock(stock: ProductStock): Number {
    this.productService.postStockObserveable(stock).subscribe(res => { this.stockId = res.stockId; });
    return this.stockId;
  }
  onSubmit(){
    this.stock.name=this.name;
    this.stock.pricePerUnit=this.pricePerUnit;
    this.stock.qualityCheck=this.qualityCheck;
    this.stock.quantityUnit=this.quantityUnit;
    this.stock.manufacturingDate=this.manufacturingDate;
    this.stock.expiryDate=this.expiryDate;
    this.stockId=this.postStock(this.stock);
    this.submitted=true;
    this.orderForm.reset();
  }

}
