package com.vendor.feign;

import com.vendor.dto.ProductStatsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/products/vendor/{vendorId}/stats")
    ProductStatsDTO getProductStats(@PathVariable("vendorId") Long vendorId);
}