package com.handler.exception.service;

import com.handler.exception.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(String id);

    List<ProductDTO> getAllProducts();

    ProductDTO createProduct(ProductDTO product);

    ProductDTO updateProduct(String id, ProductDTO product);

    void deleteProduct(String id);
}
