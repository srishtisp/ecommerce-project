import { Component, OnInit } from '@angular/core';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  standalone: false
})
export class ProductsComponent implements OnInit {

  products: any[] = [];

  product: any = {
    productName: '',
    description: '',
    price: '',
    vendorId: ''
  };

  selectedFile!: File;
  editProductId: number | null = null;

  constructor(private productService: ProductService) {}

  ngOnInit() {
    this.load();
  }

  get isEditMode(): boolean {
    return this.editProductId !== null;
  }

  load() {
    this.productService.getAll().subscribe(res => {
      this.products = res;
    });
  }

  getImageUrl(product: any): string {
    const raw = product?.imagePath ?? product?.imageUrl;
    if (!raw) {
      return '';
    }
    if (typeof raw === 'string' && (raw.startsWith('http://') || raw.startsWith('https://'))) {
      return raw;
    }
    return `/api/products/image/${raw}`;
  }

  onFileChange(e: any) {
    this.selectedFile = e.target.files[0];
  }

  save() {
    if (!this.selectedFile) {
      alert('Please choose an image file');
      return;
    }

    const fd = new FormData();
    fd.append(
      'product',
      new Blob([JSON.stringify(this.product)], { type: 'application/json' })
    );
    fd.append('file', this.selectedFile);

    this.productService.addProduct(fd).subscribe({
      next: () => {
        this.load();
        this.resetForm();
        alert('Product added');
      },
      error: (err) => {
        console.error(err);
        alert('Add failed: ' + (err?.error?.message || err?.error || err.message));
      }
    });
  }

  startEdit(p: any) {
    this.editProductId = p.productId;
    this.product = {
      productName: p.productName ?? '',
      description: p.description ?? '',
      price: p.price ?? '',
      vendorId: p.vendorId ?? ''
    };
  }

  update() {
    if (this.editProductId === null) {
      return;
    }

    this.productService.updateProduct(this.editProductId, this.product).subscribe({
      next: () => {
        this.load();
        this.resetForm();
        alert('Product updated');
      },
      error: (err) => {
        console.error(err);
        alert('Update failed: ' + (err?.error?.message || err.message));
      }
    });
  }

  cancelEdit() {
    this.resetForm();
  }

  delete(id: number) {
    this.productService.delete(id).subscribe(() => {
      this.load();
    });
  }

  updateImage(id: number, e: any) {
    const file = e.target.files[0];
    this.productService.updateImage(id, file).subscribe(() => {
      alert('Image updated');
    });
  }

  private resetForm() {
    this.product = {
      productName: '',
      description: '',
      price: '',
      vendorId: ''
    };
    this.editProductId = null;
    this.selectedFile = undefined as unknown as File;
  }
}

