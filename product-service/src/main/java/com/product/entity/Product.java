package com.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(nullable = false)
    private String productName;

    private String description;

    private double price;

    private Long vendorId;

    private boolean active = true;

    @Column(name = "image_url")
    private String imageUrl;
}