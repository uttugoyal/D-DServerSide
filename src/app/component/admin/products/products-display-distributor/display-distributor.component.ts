import { Component, OnInit } from '@angular/core';
import { Distributor } from 'src/app/models/Distributor';

import { ProductService } from 'src/app/services/product.service';

@Component({
  selector: 'app-display-distributor',
  templateUrl: './display-distributor.component.html',
  styleUrls: ['./display-distributor.component.css']
})
export class DisplayDistributorComponent implements OnInit {
  distributors:Distributor[];
  constructor(private service:ProductService) { 
    this.distributors=this.service.getDistributor();
  }

  ngOnInit(): void {
  }
}
