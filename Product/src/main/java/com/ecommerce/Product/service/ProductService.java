package com.ecommerce.Product.service;

import com.ecommerce.Product.dto.ProductRequest;
import com.ecommerce.Product.dto.ProductResponse;
import com.ecommerce.Product.model.Product;
import com.ecommerce.Product.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;


    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product();
        updateProductFromRequest(product, productRequest);
        Product savedProduct = productRepository.save(product);
        return mapToProductResponce(savedProduct);
    }

    private ProductResponse mapToProductResponce(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setCategory(savedProduct.getCategory());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setStockQuantity(savedProduct.getStockQuantity());
        response.setImageUrl(savedProduct.getImageUrl());
        response.setActive(savedProduct.isActive());
        return response;
    }

    private void updateProductFromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setImageUrl(productRequest.getImageUrl());
    }

    public List<ProductResponse> fetchAllProducts() {
      // List<Product> products = productRepository.findAll();
//       return productRepository.findAll().stream()
//               .map(this::mapToProductResponce)
//               .collect(Collectors.toList());
        return productRepository.findByActiveTrue().stream()
                .map(this::mapToProductResponce)
                .collect(Collectors.toList());
    }

    public Optional<ProductResponse> fetchProduct(Long id) {
        return productRepository.findById(id)
                .map(this::mapToProductResponce);
    }

    public Optional<ProductResponse> updateProduct(Long id, ProductRequest updatedProductRequest) {
      return productRepository.findById(id)
              .map(existingproduct -> {
                  updateProductFromRequest(existingproduct, updatedProductRequest);
                  Product savedproduct = productRepository.save(existingproduct);
                  return mapToProductResponce(savedproduct);
                 // return true;
              });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setActive(false);
                    productRepository.save(product);
                    return true;
                }).orElse(false);

//       // Changing below to Stram api (as above)
//        Product product = productRepository.findById(id)
//                .orElseThrow(()-> new RuntimeException("Product not found"));
//        product.setActive(false);
//        productRepository.save(product);
    }

    public List<ProductResponse> searchProduct(String keyword) {
        return productRepository.searchProducts(keyword).stream()
                .map(this::mapToProductResponce)
                .collect(Collectors.toList());
    }
}
