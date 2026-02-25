package com.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductDTO {

    @NotBlank
    private String productName;

    @NotBlank
    private String description;

    @NotNull
    private Double price;

    @NotNull
    private Long vendorId;
}