package com.app.product.service;

import com.app.product.model.Product;

import java.util.List;

public interface ProductService {
    Product createProduct(Product product);

    Product getProductById(String id);

    List<Product> getAllProducts();

    Product updateProduct(String id, Product updated);

    void deleteProduct(String id);
}
