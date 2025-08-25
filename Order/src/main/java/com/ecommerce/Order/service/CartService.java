package com.ecommerce.Order.service;

import com.ecommerce.Order.dto.CartItemRequest;
import com.ecommerce.Order.model.CartItem;
import com.ecommerce.Order.repositories.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
//    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;
//    private final UserRepository userRepository;

    public boolean addToCart(String userId, CartItemRequest request) {
//        // search for product
//       Optional<Product> productOpt = productRepository.findById(request.getProductId());
//       if(productOpt.isEmpty()) {
//           return false;
//       }
//       Product product = productOpt.get();
//
//       if(product.getStockQuantity() < request.getQuantity()) {
//           return false;
//       }
//
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//       if(userOpt.isEmpty()) {
//           return false;
//       }
//
//       User user = userOpt.get();

        CartItem existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, request.getProductId());
        if(existingCartItem != null) {
            //update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
//            existingCartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingCartItem.getQuantity())));
            existingCartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(existingCartItem);
        }else {
            // Create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
//            cartItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(request.getQuantity())));
            cartItem.setPrice(BigDecimal.valueOf(1000.00));
            cartItemRepository.save(cartItem);
        }

        return true;
    }

    public boolean deleteItemFromCard(String userId, String productId) {
//        Optional<Product> productOpt = productRepository.findById(productId);
////        if(productOpt.isEmpty()) {
////            return false;
////        }
//
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
////        if(userOpt.isEmpty()) {
////            return false;
////        }
        CartItem cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

//        if(productOpt.isPresent() && userOpt.isPresent()) {
        if(cartItem !=null) {
            cartItemRepository.delete(cartItem);

            return true;
        }
//        userOpt.flatMap(user ->
//                productOpt.map(product -> {
//                    cartItemRepository.deleteByUserAndProduct(user, product);
//                    return true;
//                }));
        return false;
    }

    public List<CartItem> getAllCartItems(String userId) {
        return cartItemRepository.findByUserId(userId);
    }
//        return userRepository.findById(Long.valueOf(userId))
//                        .map(cartItemRepository::findByUser)
//                                .orElseGet(List::of);
//        }

    public void clearCart(String userId) {
//        userRepository.findById(Long.valueOf(userId)).ifPresent(
//                cartItemRepository::deleteByUser);
        cartItemRepository.deleteByUserId(userId);
    }
}
