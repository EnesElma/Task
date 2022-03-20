package com.enes.product.service;

import com.enes.product.entity.Product;

import java.util.List;

public interface IProductService {
    Product createProduct(Product product);

    Product updateProduct(Product request);

    void deleteProduct(Long id, String username);

    List<Product> listAllProduct();
}
