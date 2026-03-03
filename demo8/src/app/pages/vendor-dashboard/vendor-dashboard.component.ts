import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-vendor-dashboard',
  templateUrl: './vendor-dashboard.component.html',
  standalone: false
})
export class VendorDashboardComponent implements OnInit {

  vendorId!: number;
  stats:any;

  constructor(
    private route:ActivatedRoute,
    private productService:ProductService
  ){}

  ngOnInit() {

    this.vendorId =
      Number(this.route.snapshot.paramMap.get('vendorId'));

    this.productService.getStats(this.vendorId)
      .subscribe(r=>{
        this.stats=r;
      });

  }

}

