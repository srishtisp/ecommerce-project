package com.product.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String productName;
    private String description;
    private double price;
    private Long vendorId;
}