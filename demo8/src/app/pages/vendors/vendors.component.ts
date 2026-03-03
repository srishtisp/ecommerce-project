import { Component, OnInit } from '@angular/core';
import { VendorService } from '../../services/vendor.service';

@Component({
  selector: 'app-vendors',
  templateUrl: './vendors.component.html',
  standalone: false
})
export class VendorsComponent implements OnInit {

  vendors:any[]=[];
  showOnlyActive = true;

  constructor(private vendorService:VendorService){}

  ngOnInit() {
    this.vendorService.getAll().subscribe(r=>{
      this.vendors=r;
    })
  }

  get filteredVendors(): any[] {
    if (!this.showOnlyActive) {
      return this.vendors;
    }
    return this.vendors.filter(v => v.active === true);
  }
}

