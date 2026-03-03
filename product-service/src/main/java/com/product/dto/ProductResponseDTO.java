package com.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private Long vendorId;
    private boolean active;
    private String imageUrl;
}