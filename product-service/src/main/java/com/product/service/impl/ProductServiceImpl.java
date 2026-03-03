package com.product.service.impl;

import com.product.dto.*;
import com.product.entity.Product;
import com.product.feign.VendorClient;
import com.product.repository.ProductRepository;
import com.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repo;
    private final VendorClient vendorClient;

    private final String UPLOAD_DIR = "uploads/";

    private ProductResponseDTO mapToDTO(Product p) {
        return new ProductResponseDTO(
                p.getProductId(),
                p.getProductName(),
                p.getDescription(),
                p.getPrice(),
                p.getVendorId(),
                p.isActive(),
                p.getImageUrl()
        );
    }

    @Override
    public ProductResponseDTO addProduct(ProductDTO dto, MultipartFile file) {

        // ✅ Validate vendor
        vendorClient.getVendor(dto.getVendorId());

        String imageUrl = null;

        try {
            if (file != null && !file.isEmpty()) {

                String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path path = Paths.get(UPLOAD_DIR + fileName);
                Files.createDirectories(path.getParent());
                Files.write(path, file.getBytes());

                imageUrl = "http://localhost:8082/products/image/" + fileName;
            }
        } catch (Exception e) {
            throw new RuntimeException("Image upload failed");
        }

        Product p = new Product();
        p.setProductName(dto.getProductName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setVendorId(dto.getVendorId());
        p.setActive(true);
        p.setImageUrl(imageUrl);

        return mapToDTO(repo.save(p));
    }

    @Override
    public ProductResponseDTO updateProduct(Long id, ProductDTO dto) {
        Product p = repo.findById(id).orElseThrow();

        p.setProductName(dto.getProductName());
        p.setDescription(dto.getDescription());
        p.setPrice(dto.getPrice());
        p.setVendorId(dto.getVendorId());

        return mapToDTO(repo.save(p));
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
        return mapToDTO(repo.findById(id).orElseThrow());
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
        Product p = repo.findById(id).orElseThrow();
        p.setActive(active);
        repo.save(p);
    }

    @Override
    public ProductStatsDTO getProductStats(Long vendorId) {
        List<Product> products = repo.findByVendorId(vendorId);
        int total = products.size();
        int active = (int) products.stream().filter(Product::isActive).count();
        return new ProductStatsDTO(total, active);
    }
}