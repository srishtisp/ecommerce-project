package com.vendor.service.impl;

import com.vendor.dto.ProductStatsDTO;
import com.vendor.dto.VendorDTO;
import com.vendor.dto.VendorDashboardDTO;
import com.vendor.dto.VendorResponseDTO;
import com.vendor.entity.Vendor;
import com.vendor.feign.ProductClient;
import com.vendor.repository.VendorRepository;
import com.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repo;
    private final ProductClient productClient;   // ⭐ Feign Client

    private static final Logger log = LoggerFactory.getLogger(VendorServiceImpl.class);

    // 🔹 Convert Entity → Response DTO
    private VendorResponseDTO mapToDTO(Vendor vendor) {
        return new VendorResponseDTO(
                vendor.getVendorId(),
                vendor.getVendorName(),
                vendor.getCategory(),
                vendor.isActive()
        );
    }

    // 🔹 Convert DTO → Entity
    private Vendor mapToEntity(VendorDTO dto) {
        Vendor vendor = new Vendor();
        vendor.setVendorName(dto.getVendorName());
        vendor.setCategory(dto.getCategory());
        vendor.setActive(true);
        return vendor;
    }

    // ✅ Add Vendor
    @Override
    public VendorResponseDTO addVendor(VendorDTO dto) {
        Vendor vendor = mapToEntity(dto);
        Vendor saved = repo.save(vendor);

        log.info("Vendor added: {}", saved.getVendorName());
        return mapToDTO(saved);
    }

    // ✅ Update Vendor
    @Override
    public VendorResponseDTO updateVendor(Long id, VendorDTO dto) {
        Vendor vendor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id " + id));

        vendor.setVendorName(dto.getVendorName());
        vendor.setCategory(dto.getCategory());

        Vendor updated = repo.save(vendor);
        log.info("Vendor updated: {}", updated.getVendorName());

        return mapToDTO(updated);
    }

    // ✅ Activate / Deactivate Vendor
    @Override
    public void toggleVendorStatus(Long id, boolean active) {
        Vendor vendor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id " + id));

        vendor.setActive(active);
        repo.save(vendor);

        log.info("Vendor {} status changed to {}", vendor.getVendorName(), active);
    }

    // ✅ Get All Vendors
    @Override
    public List<VendorResponseDTO> getAllVendors() {
        return repo.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Get Vendor by ID
    @Override
    public VendorResponseDTO getVendorById(Long id) {
        Vendor vendor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id " + id));

        return mapToDTO(vendor);
    }

    // ✅ Search by Name
    @Override
    public List<VendorResponseDTO> searchByName(String name) {
        return repo.findByVendorNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Find by Category
    @Override
    public List<VendorResponseDTO> findByCategory(String category) {
        return repo.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Vendor Dashboard (Feign Integration)
    @Override
    public VendorDashboardDTO getDashboard(Long vendorId) {

        Vendor vendor = repo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // ⭐ Call Product Service via Feign
        ProductStatsDTO stats = productClient.getProductStats(vendorId);

        log.info("Fetched product stats for vendor {}: total={}, active={}",
                vendorId, stats.getTotalProducts(), stats.getActiveProducts());

        return new VendorDashboardDTO(
                vendor.getVendorId(),
                vendor.getVendorName(),
                stats.getTotalProducts(),
                stats.getActiveProducts()
        );
    }
}