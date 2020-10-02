import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Supplier } from 'src/app/models/Supplier';
import { RawmaterialService } from 'src/app/services/rawmaterial.service';

@Component({
  selector: 'app-supplier',
  templateUrl: './supplier.component.html',
  styleUrls: ['./supplier.component.css']
})
export class SupplierComponent implements OnInit {
  @ViewChild('f', { static: false }) supplierForm: NgForm;
  supplier: Supplier = new Supplier();
   supplierId:Number;
  name:string;
  location:string;
  phoneNumber:number;
  submitted = false;

  constructor(private rawmaterialService: RawmaterialService) { 
   
  }

  ngOnInit(): void {
  }
 postSupplier(supplier: Supplier): Number {
    this.rawmaterialService.postSupplierObserveable(supplier).subscribe(res => { this.supplierId = res.supplierId; });
    return this.supplierId;
  }

  onSubmit(){
    this.supplier.name=this.name;
    this.supplier.location=this.location;
    this.supplier.phoneNumber=this.phoneNumber;
    this.supplierId=this.postSupplier(this.supplier);
    this.submitted=true;
    this.supplierForm.reset();
  }


}
