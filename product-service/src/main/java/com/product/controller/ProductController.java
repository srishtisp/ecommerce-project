package com.product.controller;

import com.product.dto.ProductDTO;
import com.product.dto.ProductResponseDTO;
import com.product.dto.ProductStatsDTO;
import com.product.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductResponseDTO addProduct(@Valid @RequestBody ProductDTO dto) {
        return service.addProduct(dto);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id,
                                            @Valid @RequestBody ProductDTO dto) {
        return service.updateProduct(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        service.deleteProduct(id);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return service.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductResponseDTO> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    @GetMapping("/vendor/{vendorId}")
    public List<ProductResponseDTO> byVendor(@PathVariable Long vendorId) {
        return service.getProductsByVendor(vendorId);
    }

    @PatchMapping("/{id}/status")
    public void toggleStatus(@PathVariable Long id, @RequestParam boolean active) {
        service.toggleProductStatus(id, active);
    }
    @GetMapping("/vendor/{vendorId}/stats")
    public ProductStatsDTO getStats(@PathVariable Long vendorId) {
        return service.getProductStats(vendorId);
    }
}