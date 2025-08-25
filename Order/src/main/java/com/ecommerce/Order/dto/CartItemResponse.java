package com.ecommerce.Order.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartItemResponse {
    private Long id;
    private String userId;
    private String productId;
    private Integer stockQuantity;
    private BigDecimal price;
}
