import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { RawmaterialService } from 'src/app/services/rawmaterial.service';
import { HttpClient } from '@angular/common/http';
import { CustomResponse } from 'src/app/models/CustomResponse';
import { RawMaterialOrder } from 'src/app/models/RawMaterialOrder';

@Component({
  selector: 'app-rawmaterial-track',
  templateUrl: './rawmaterial-track.component.html',
  styleUrls: ['./rawmaterial-track.component.css']
})
export class RawmaterialTrackComponent implements OnInit {
  @ViewChild('f', { static: false }) signupForm: NgForm;
  rawMaterialOrder : RawMaterialOrder;
  trackResponse:string;
  submitted:boolean=false;
  orderId:number;
  rawMaterialOrders:RawMaterialOrder[];
  order:RawMaterialOrder
  readonly baseUrl = 'http://localhost:1119/rawOrder';
  constructor(private http:HttpClient,rawMaterialService:RawmaterialService) {
    this.rawMaterialOrders=rawMaterialService.getRawMaterialOrders();
    console.log(this.rawMaterialOrders)
   }

  ngOnInit(): void {
  }
  trackRawMaterialOrder(Id:number):RawMaterialOrder{
    this.http.get<RawMaterialOrder>(this.baseUrl+"/trackOrder/"+Id).subscribe((res)=>{
      console.log(res);
      this.rawMaterialOrder=res;
    });
    return this.rawMaterialOrder;
  }

  onSubmit(){
    this.orderId=this.order.orderId;
    this.trackRawMaterialOrder(this.orderId);
    this.submitted=true;
    this.signupForm.reset();
  }
}
