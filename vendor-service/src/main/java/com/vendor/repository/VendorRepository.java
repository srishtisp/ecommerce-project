package com.vendor.repository;

import com.vendor.entity.Vendor;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
	List<Vendor> findByVendorNameContainingIgnoreCase(String name);

    List<Vendor> findByCategoryIgnoreCase(String category);
}