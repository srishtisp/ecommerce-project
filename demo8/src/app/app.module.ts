import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { ProductsComponent } from './pages/products/products.component';
import { VendorsComponent } from './pages/vendors/vendors.component';
import { VendorDashboardComponent } from './pages/vendor-dashboard/vendor-dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    ProductsComponent,
    VendorsComponent,
    VendorDashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }