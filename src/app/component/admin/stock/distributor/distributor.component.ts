import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Distributor } from 'src/app/models/Distributor';
import { ProductService } from 'src/app/services/product.service';
@Component({
  selector: 'app-distributor',
  templateUrl: './distributor.component.html',
  styleUrls: ['./distributor.component.css']
})
export class DistributorComponent implements OnInit {
  @ViewChild('f', { static: false }) distributorForm: NgForm;
  distributor: Distributor = new Distributor();
  distributorId:Number;
  name:string;
  location:string;
  phoneNumber:number;
  submitted = false;

  constructor(private productService: ProductService) { 
   
  }

  ngOnInit(): void {
  }
 postDistributor(distributor: Distributor): Number {
    this.productService.postDistributorObserveable(distributor).subscribe(res => { this.distributorId = res.distributorId; });
    return this.distributorId;
  }

  onSubmit(){
    this.distributor.name=this.name;
    this.distributor.location=this.location;
    this.distributor.phoneNumber=this.phoneNumber;
    this.distributorId=this.postDistributor(this.distributor);
    this.submitted=true;
    this.distributorForm.reset();
  }

}
