package com.product.service.impl;

import com.product.dto.ProductDTO;
import com.product.dto.ProductResponseDTO;
import com.product.dto.ProductStatsDTO;
import com.product.entity.Product;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;

    @Override
    public ProductResponseDTO addProduct(ProductDTO dto) {
        Product product = new Product(null, dto.getProductName(),
                dto.getDescription(), dto.getPrice(),
                dto.getVendorId(), true);

        return mapToDTO(repo.save(product));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductDTO dto) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setProductName(dto.getProductName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());

        return mapToDTO(repo.save(product));
    }

    @Override
    public void deleteProduct(Long id) {
        repo.deleteById(id);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductResponseDTO getProductById(Long id) {
        return mapToDTO(repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")));
    }

    @Override
    public List<ProductResponseDTO> searchByName(String name) {
        return repo.findByProductNameContainingIgnoreCase(name)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ProductResponseDTO> getProductsByVendor(Long vendorId) {
        return repo.findByVendorId(vendorId)
                .stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public void toggleProductStatus(Long id, boolean active) {
        Product product = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setActive(active);
        repo.save(product);
    }

    private ProductResponseDTO mapToDTO(Product p) {
        return ProductResponseDTO.builder()
                .productId(p.getProductId())
                .productName(p.getProductName())
                .description(p.getDescription())
                .price(p.getPrice())
                .vendorId(p.getVendorId())
                .active(p.isActive())
                .build();
    }
    @Override
    public ProductStatsDTO getProductStats(Long vendorId) {
        int total = (int) repo.countByVendorId(vendorId);
        int active = (int) repo.countByVendorIdAndActiveTrue(vendorId);

        return new ProductStatsDTO(total, active);
    }
}
