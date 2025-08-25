package com.ecommerce.Order.controller;

import com.ecommerce.Order.dto.CartItemRequest;
import com.ecommerce.Order.model.CartItem;
import com.ecommerce.Order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    // Add products to the cart
    @PostMapping
    public ResponseEntity<String> addToCart(@RequestHeader("X-User-ID") String userID,
                                          @RequestBody CartItemRequest request){
       if(!cartService.addToCart(userID, request)){
           return ResponseEntity.badRequest().body("Product Out of stock or user not found or product not found");
       }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/item/{productId}")
    public ResponseEntity<Void> removeFromCart(@RequestHeader("X-User-ID") String userId,
                                               @PathVariable String productId) {
       boolean deleted = cartService.deleteItemFromCard(userId,productId);
       return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();

    }

    @GetMapping()
    public ResponseEntity<List<CartItem>> getCartItems(@RequestHeader("X-User-ID") String userId) {
        return ResponseEntity.ok(cartService.getAllCartItems(userId));
    }
}