package com.app.product.service.impl;

import com.app.product.exception.ProductNotFoundException;
import com.app.product.service.ProductService;
import com.app.product.model.Product;
import com.app.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getId() == null || product.getId().isEmpty()) {
            product.setId(UUID.randomUUID().toString());
        }
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductById(String id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with ID: " + id);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String id, Product updated) {
        Product existingProduct = getProductById(id);
        existingProduct.setName(updated.getName());
        existingProduct.setDescription(updated.getDescription());
        existingProduct.setPrice(updated.getPrice());
        productRepository.save(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProduct(String id) {
        Product product = getProductById(id);
        productRepository.deleteById(id);
    }
}
