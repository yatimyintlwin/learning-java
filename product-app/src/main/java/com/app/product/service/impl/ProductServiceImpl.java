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
        product.setPk("product");
        productRepository.save(product);
        return product;
    }

    @Override
    public Product getProductByPkAndId(String pk, String id) {
        Product product = productRepository.findByPkAndId(pk, id);
        if (product == null) {
            throw new ProductNotFoundException("Product not found with pk: " + pk + " and id: " + id);
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAll();
    }

    @Override
    public Product updateProduct(String pk, String id, Product updated) {
        Product existingProduct = productRepository.findByPkAndId(pk, id);
        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found with pk: " + pk + " and id: " + id);
        }
        existingProduct.setName(updated.getName());
        existingProduct.setDescription(updated.getDescription());
        existingProduct.setPrice(updated.getPrice());
        existingProduct.setPk(pk);
        productRepository.update(existingProduct);
        return existingProduct;
    }

    @Override
    public void deleteProduct(String pk, String id) {
        Product product = productRepository.findByPkAndId(pk, id);
        if (product == null) {
            throw new ProductNotFoundException("Cannot delete. Product with pk " + pk + " and id " + id + " not found.");
        }
        productRepository.deleteByPkAndId(pk, id);
    }
}
