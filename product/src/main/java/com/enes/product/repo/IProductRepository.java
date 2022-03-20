package com.enes.product.repo;

import com.enes.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IProductRepository extends JpaRepository<Product,Long> {
    Optional<Product> findByName(String name);

    void deleteByIdAndUsername(Long id,String username);
}
