import { Component, OnInit } from '@angular/core';
import { ProductStock } from 'src/app/models/ProductStock';
import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-display-product-stock',
  templateUrl: './display-product-stock.component.html',
  styleUrls: ['./display-product-stock.component.css']
})
export class DisplayProductStockComponent implements OnInit {
    productStock:ProductStock[];
  constructor(private service:ProductService) {
    this.productStock=this.service.getProductStock();
   }

  ngOnInit(): void {
  }

}
