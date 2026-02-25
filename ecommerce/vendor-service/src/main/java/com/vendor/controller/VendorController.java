package com.vendor.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vendor.dto.VendorDTO;
import com.vendor.dto.VendorDashboardDTO;
import com.vendor.dto.VendorResponseDTO;
import com.vendor.service.VendorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
@Tag(name = "Vendor API", description = "Vendor management endpoints")
public class VendorController {

    private final VendorService service;

    @Operation(summary = "Add new vendor")
    @PostMapping
    public VendorResponseDTO addVendor(@RequestBody VendorDTO dto) {
        return service.addVendor(dto);
    }

    @Operation(summary = "Update vendor")
    @PutMapping("/{id}")
    public VendorResponseDTO updateVendor(@PathVariable Long id, @RequestBody VendorDTO dto) {
        return service.updateVendor(id, dto);
    }

    @Operation(summary = "Activate or deactivate vendor")
    @PatchMapping("/{id}/status")
    public void toggleStatus(@PathVariable Long id, @RequestParam boolean active) {
        service.toggleVendorStatus(id, active);
    }

    @Operation(summary = "Get all vendors")
    @GetMapping
    public List<VendorResponseDTO> getAll() {
        return service.getAllVendors();
    }

    @Operation(summary = "Get vendor by ID")
    @GetMapping("/{id}")
    public VendorResponseDTO getById(@PathVariable Long id) {
        return service.getVendorById(id);
    }

    @Operation(summary = "Search vendors by name")
    @GetMapping("/search")
    public List<VendorResponseDTO> search(@RequestParam String name) {
        return service.searchByName(name);
    }

    @Operation(summary = "Find vendors by category")
    @GetMapping("/category/{category}")
    public List<VendorResponseDTO> byCategory(@PathVariable String category) {
        return service.findByCategory(category);
    }

    @Operation(summary = "Vendor dashboard summary")
    @GetMapping("/{id}/dashboard")
    public VendorDashboardDTO dashboard(@PathVariable Long id) {
        return service.getDashboard(id);
    }
}