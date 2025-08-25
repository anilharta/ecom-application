package com.ecommerce.Product.repositories;

import com.ecommerce.Product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByActiveTrue();

    @Query("select p from Products p where p.active = true and p.stockQuantity > 0 and lower(p.name) like lower (concat('%', :keyword, '%'))")
    List<Product> searchProducts(@Param("keyword") String keyword);

}
