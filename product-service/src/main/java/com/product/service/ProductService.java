package com.product.service;

import com.product.dto.ProductDTO;
import com.product.dto.ProductResponseDTO;
import com.product.dto.ProductStatsDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductDTO dto);

    ProductResponseDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> searchByName(String name);

    List<ProductResponseDTO> getProductsByVendor(Long vendorId);

    void toggleProductStatus(Long id, boolean active);
    ProductStatsDTO getProductStats(Long vendorId);
}