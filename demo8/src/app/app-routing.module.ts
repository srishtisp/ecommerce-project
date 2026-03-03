import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProductsComponent } from './pages/products/products.component';
import { VendorsComponent } from './pages/vendors/vendors.component';
import { VendorDashboardComponent } from './pages/vendor-dashboard/vendor-dashboard.component';

const routes: Routes = [
  { path: 'products', component: ProductsComponent },
  { path: 'vendors', component: VendorsComponent },
  { path: 'dashboard/:vendorId', component: VendorDashboardComponent },
  { path: '', redirectTo: 'products', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }