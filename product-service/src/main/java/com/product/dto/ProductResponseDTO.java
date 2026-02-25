package com.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {
    private Long productId;
    private String productName;
    private String description;
    private double price;
    private Long vendorId;
    private boolean active;
}
