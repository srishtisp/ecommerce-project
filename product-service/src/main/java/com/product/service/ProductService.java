package com.product.service;

import com.product.dto.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProductService {

    ProductResponseDTO addProduct(ProductDTO dto, MultipartFile file);

    ProductResponseDTO updateProduct(Long id, ProductDTO dto);

    void deleteProduct(Long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO getProductById(Long id);

    List<ProductResponseDTO> searchByName(String name);

    List<ProductResponseDTO> getProductsByVendor(Long vendorId);

    void toggleProductStatus(Long id, boolean active);

    ProductStatsDTO getProductStats(Long vendorId);
}