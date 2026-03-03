import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = '/api/products';

  constructor(private http: HttpClient) {}

  getAll() {
    return this.http.get<any[]>(this.baseUrl);
  }

  addProduct(formData: FormData) {
    return this.http.post(this.baseUrl, formData);
  }

  updateProduct(id: number, dto: any) {
    return this.http.put(`${this.baseUrl}/${id}`, dto);
  }

  delete(id: number) {
    return this.http.delete(`${this.baseUrl}/${id}`);
  }

  updateImage(id: number, file: File) {
    const fd = new FormData();
    fd.append('file', file);
    return this.http.put(`${this.baseUrl}/${id}/image`, fd);
  }

  getStats(vendorId: number) {
    return this.http.get(
      `${this.baseUrl}/vendor/${vendorId}/stats`
    );
  }
}


