package com.product.feign;

import com.product.dto.VendorDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "vendor-service", url = "http://localhost:8081/vendors")
public interface VendorClient {

    @GetMapping("/{id}")
    VendorDTO getVendor(@PathVariable Long id);
}