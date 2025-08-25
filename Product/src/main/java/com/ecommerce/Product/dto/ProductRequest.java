package com.ecommerce.Product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private Integer stockQuantity;
    private String category;
    private String description;
    private String imageUrl;

}
