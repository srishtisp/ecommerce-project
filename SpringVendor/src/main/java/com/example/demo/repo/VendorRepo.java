package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Vendor;

public interface VendorRepo extends JpaRepository <Vendor,Integer>{
		Optional<Vendor> findByVendorName(String vendorName);
}
