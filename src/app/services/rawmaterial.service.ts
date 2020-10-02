import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RawMaterialOrder } from '../models/RawMaterialOrder';
import { Observable,Subject } from 'rxjs';
import { Supplier } from '../models/Supplier';
import { RawMaterialStock } from '../models/RawMaterialStock';
import { CustomResponse } from '../models/CustomResponse';

@Injectable({
  providedIn: 'root'
})
export class RawmaterialService {
  orderId: number;
  stockUpdateResponse:string="updated";
  private trackResponse:string;
  private suppliers:Supplier[];
  private stockIds:number[];
  private stockNames:string[];
  private rawMaterialOrders:RawMaterialOrder[];
  readonly rawmaterialOrderUrl = 'http://localhost:1119/rawOrder';
  readonly rawmaterialStockUrl = 'http://localhost:1119/rawStock';


  constructor(private http: HttpClient) {
  }

  getSuppliers():Supplier[]{
    this.suppliers = [];
    this.http.get<Supplier[]>(this.rawmaterialOrderUrl + '/getSuppliers').subscribe((suppliersres) => {
      Object.keys(suppliersres).map(key => {
        this.suppliers.push(suppliersres[key]);
      });
    });
    return this.suppliers;
  }
  
  getRawMaterialStockId():number[]{
    this.stockIds = [];
    this.http.get<Number[]>(this.rawmaterialStockUrl + '/getRawMaterialIds').subscribe((idres) => {
      Object.keys(idres).map(key => {
        this.stockIds.push(idres[key].valueOf());
      });
    });
    return this.stockIds;
  }

  
  getRawMaterialStockNames():string[]{
    this.stockNames = [];
    this.http.get<String[]>(this.rawmaterialStockUrl + '/getRawMaterialNames').subscribe((nres) => {
      Object.keys(nres).map(key => {
        this.stockNames.push(nres[key].valueOf());
      });
    });
    return this.stockNames;
  }
 

  postOrderObserveable(order:RawMaterialOrder) :Observable<RawMaterialOrder>{
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<RawMaterialOrder>(this.rawmaterialOrderUrl + '/addOrder', JSON.stringify(order), { headers: httpHeader });
  }

  postStockObserveable(stock:RawMaterialStock) :Observable<RawMaterialStock>{
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<RawMaterialStock>(this.rawmaterialStockUrl + '/addStock', JSON.stringify(stock), { headers: httpHeader });
  }


  postSupplierObserveable(supplier : Supplier): Observable<Supplier>{
    const httpHeader = new HttpHeaders({ 'Content-Type': 'application/json' })
    return this.http.post<Supplier>(this.rawmaterialStockUrl + '/addSupplier', JSON.stringify(supplier), { headers: httpHeader });
  }


  getRawMaterialOrders():RawMaterialOrder[]{
    this.rawMaterialOrders = [];
    this.http.get<RawMaterialOrder[]>(this.rawmaterialOrderUrl + '/getRawmaterialOrders').subscribe((rawMaterialOrdersres) => {
      Object.keys(rawMaterialOrdersres).map(key => {
        this.rawMaterialOrders.push(rawMaterialOrdersres[key]);
      });
    });
    return this.rawMaterialOrders;
  }


  updateStock(stockId:Number,unit:Number,price:Number,quality:String):string{
    this.http.get<CustomResponse>(this.rawmaterialStockUrl+"/updateStock/"+stockId+"/"+unit+"/"+price+"/"+quality).subscribe((res)=>{
      console.log(res);
      this.stockUpdateResponse=res.responseMessage;
    });
    return this.stockUpdateResponse;
  }


  trackRawMaterialOrder(Id:number):string{
    this.trackResponse="";
    this.http.get<CustomResponse>(this.rawmaterialOrderUrl+"/trackOrder/"+Id).subscribe((res)=>{
      console.log(res.responseMessage);
      this.trackResponse=res.responseMessage;
    });
    return this.trackResponse;
  }


  

}
