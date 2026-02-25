package com.vendor.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VendorResponseDTO {
    private Long vendorId;
    private String vendorName;
    private String category;
    private boolean active;
}