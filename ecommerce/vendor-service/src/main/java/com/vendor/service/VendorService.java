package com.vendor.service;

import java.util.List;
import com.vendor.dto.*;

public interface VendorService {

    VendorResponseDTO addVendor(VendorDTO dto);

    VendorResponseDTO updateVendor(Long id, VendorDTO dto);

    void toggleVendorStatus(Long id, boolean active);

    List<VendorResponseDTO> getAllVendors();

    VendorResponseDTO getVendorById(Long id);

    List<VendorResponseDTO> searchByName(String name);

    List<VendorResponseDTO> findByCategory(String category);

    VendorDashboardDTO getDashboard(Long vendorId);
}