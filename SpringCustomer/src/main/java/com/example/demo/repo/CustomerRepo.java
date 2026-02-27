package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Customer;

public interface CustomerRepo extends JpaRepository <Customer,Integer>{
	Optional<Customer> findByCustomerName(String customerName);
}
