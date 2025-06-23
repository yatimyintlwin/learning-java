package com.handler.exception.service.impl;

import com.handler.exception.dto.ProductDTO;
import com.handler.exception.exception.InvalidProductException;
import com.handler.exception.exception.ProductNotFoundException;
import com.handler.exception.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final Map<String, ProductDTO> productMap = new HashMap<>();

    @Override
    public ProductDTO getProductById(String id) {
        logger.info("Getting product by ID: {}", id);
        if (!productMap.containsKey(id)) {
            logger.warn("Product with ID {} not found", id);
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        logger.debug("Product Map content: {}", productMap);
        return productMap.get(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        logger.info("Getting all products. Total: {}", productMap.size());
        return productMap.values().stream().toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        logger.info("Creating product: {}", productDTO);
        if (productDTO.getId() == null || productDTO.getId().isBlank()) {
            logger.error("Product ID is empty.");
            throw new InvalidProductException("Product ID must not be empty.");
        }
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            logger.error("Product name is empty.");
            throw new InvalidProductException("Product name must not be empty.");
        }
        if (productMap.containsKey(productDTO.getId())) {
            logger.error("Product with ID {} already exists.", productDTO.getId());
            throw new InvalidProductException("Product with ID " + productDTO.getId() + " already exists.");
        }
        productMap.put(productDTO.getId(), productDTO);
        logger.info("Product with ID {} created successfully.", productDTO.getId());
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        logger.info("Updating product with ID: {}", id);
        if (!productMap.containsKey(id)) {
            logger.warn("Cannot update. Product with ID {} not found.", id);
            throw new ProductNotFoundException("Cannot update. Product with ID " + id + " not found.");
        }
        if (productDTO.getId() == null) {
            logger.error("Product ID must not be null.");
            throw new InvalidProductException("Product ID must not be null.");
        }
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            logger.error("Product name must not be empty.");
            throw new InvalidProductException("Product name must not be empty.");
        }

        productMap.put(id, productDTO);
        logger.info("Product with ID {} updated successfully.", id);
        return productDTO;
    }

    @Override
    public void deleteProduct(String id) {
        logger.info("Deleting product with ID: {}", id);
        if (!productMap.containsKey(id)) {
            logger.warn("Cannot delete. Product with ID {} not found.", id);
            throw new ProductNotFoundException("Cannot delete. Product with ID " + id + " not found.");
        }
        productMap.remove(id);
        logger.info("Product with ID {} deleted successfully.", id);
    }
}