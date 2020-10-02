import { Component, OnInit } from '@angular/core';
import { Supplier } from 'src/app/models/Supplier';
import { NetinfraService } from 'src/app/services/netinfra.service';
import { RawmaterialService } from 'src/app/services/rawmaterial.service';

@Component({
  selector: 'app-rawmaterial-supplier',
  templateUrl: './rawmaterial-supplier.component.html',
  styleUrls: ['./rawmaterial-supplier.component.css']
})
export class RawmaterialSupplierComponent implements OnInit {
  suppliers:Supplier[];
  constructor(private service:RawmaterialService) { 
    this.suppliers=this.service.getSuppliers();
  }

  ngOnInit(): void {
  }

}
