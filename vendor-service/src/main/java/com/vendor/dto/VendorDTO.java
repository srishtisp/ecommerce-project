package com.vendor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VendorDTO {

    @NotBlank
    private String vendorName;

    @NotBlank
    private String category;
}