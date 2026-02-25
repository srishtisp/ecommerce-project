package com.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendorDashboardDTO {
    private Long vendorId;
    private String vendorName;
    private int totalProducts;
    private int activeProducts;
}