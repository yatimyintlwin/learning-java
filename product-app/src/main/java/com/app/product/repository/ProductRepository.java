package com.app.product.repository;

import com.app.product.model.Product;

import java.util.List;

public interface ProductRepository {

    void save(Product product);

    void update(Product product);

    Product findByPkAndId(String pk, String id);

    List<Product> getAll();

    void deleteByPkAndId(String pk, String id);
}