package com.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductStatsDTO {
    private int totalProducts;
    private int activeProducts;
}