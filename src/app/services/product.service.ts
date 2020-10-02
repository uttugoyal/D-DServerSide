import { Injectable } from '@angular/core';
import { ProductOrder } from '../models/ProductOrder';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { CustomResponse } from '../models/CustomResponse';
import { Distributor } from '../models/Distributor';
import { ProductStock } from '../models/ProductStock';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  orderId: number;
  updateResponse: string = "success";
  stockUpdateResponse: string = "updated";
  private trackResponse: string;
  private distributors: Distributor[];
  private ProductOrders: ProductOrder[];
  private stockIds:number[];
  private stockNames:string[];
  private distributorsIds:number[];
  private productStocks:ProductStock[];
  readonly productOrderUrl = 'http://localhost:1119/productOrder';
  readonly rawmarerialOrder = 'http://localhost:8008/api/v1';
  readonly productStockUrl = 'http://localhost:1119/productStock';


  constructor(private http: HttpClient) {
  }

  getdistributors(): Distributor[] {
    this.distributors = [];
    this.http.get<Distributor[]>(this.productOrderUrl + '/getDistributors').subscribe((distributorsres) => {
      Object.keys(distributorsres).map(key => {
        this.distributors.push(distributorsres[key]);
      });
    });
    return this.distributors;
  }
 

  getProductStockId():number[]{
    this.stockIds = [];
    this.http.get<Number[]>(this.productStockUrl + '/getProductIds').subscribe((idres) => {
      Object.keys(idres).map(key => {
        this.stockIds.push(idres[key].valueOf());
      });
    });
    return this.stockIds;  
  }

  getDistributorId():number[]{
    this.distributorsIds = [];
    this.http.get<Number[]>(this.productStockUrl + '/getProductIds').subscribe((idres) => {
      Object.keys(idres).map(key => {
        this.distributorsIds.push(idres[key].valueOf());
      });
    });
    return this.distributorsIds;
  }

  getProductStockNames():string[]{
    this.stockNames = [];
    this.http.get<String[]>(this.productStockUrl + '/getProductNames').subscribe((nres) => {
      Object.keys(nres).map(key => {
        this.stockNames.push(nres[key].valueOf());
      });
    });
    return this.stockNames;
  }

  postOrderObserveable(order: ProductOrder): Observable<ProductOrder> {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<ProductOrder>(this.productOrderUrl + '/addOrder', JSON.stringify(order), { headers: httpHeader });
  }

  postStockObserveable(stock: ProductStock): Observable<ProductStock> {
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<ProductStock>(this.productStockUrl + '/addStock', JSON.stringify(stock), { headers: httpHeader });
  }

  postDistributorObserveable(distributor : Distributor): Observable<Distributor>{
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<Distributor>(this.productStockUrl + '/addDistributor', JSON.stringify(distributor), { headers: httpHeader });
  }
  getProductOrders(): ProductOrder[] {
    this.ProductOrders = [];
    this.http.get<ProductOrder[]>(this.productOrderUrl + '/getProductOrders').subscribe((ProductOrdersres) => {
      Object.keys(ProductOrdersres).map(key => {
        this.ProductOrders.push(ProductOrdersres[key]);
      });
    });
    return this.ProductOrders;
  }
  updateStock(stockId: Number, unit: Number, price: Number, quality: String): string {
    this.http.get<CustomResponse>(this.productStockUrl + "/updateStock/" + stockId + "/" + unit + "/" + price + "/" + quality).subscribe((res) => {
      console.log(res);
      this.stockUpdateResponse = res.responseMessage;
    });
    return this.stockUpdateResponse;
  }
  trackProductOrder(Id: number): string {
    this.trackResponse = "";
    this.http.get<CustomResponse>(this.productOrderUrl + "/trackOrder/" + Id).subscribe((res) => {
      console.log(res.responseMessage);
      this.trackResponse = res.responseMessage;
    });
    return this.trackResponse;
  }
  getDistributor():Distributor[]{
    this.distributors = [];
    this.http.get<Distributor[]>(this.productOrderUrl + '/getDistributors').subscribe((distributorsres) => {
      Object.keys(distributorsres).map(key => {
        this.distributors.push(distributorsres[key]);
      });
    });
    return this.distributors;
  }

  getProductStock():ProductStock[]{
    this.productStocks = [];
    this.http.get<ProductStock[]>(this.productStockUrl + '/getProductStock').subscribe((productStocks) => {
      Object.keys(productStocks).map(key => {
        this.productStocks.push(productStocks[key]);
      });
    });
    return this.productStocks;
  }

}

