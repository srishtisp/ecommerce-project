package com.product.dto;

import lombok.Data;

@Data
public class VendorDTO {
    private Long vendorId;
    private String vendorName;
    private boolean active;
}