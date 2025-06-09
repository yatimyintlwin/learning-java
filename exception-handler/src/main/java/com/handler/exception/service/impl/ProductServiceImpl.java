package com.handler.exception.service.impl;

import com.handler.exception.dto.ProductDTO;
import com.handler.exception.exception.InvalidProductException;
import com.handler.exception.exception.ProductNotFoundException;
import com.handler.exception.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    private final Map<String, ProductDTO> productMap = new HashMap<>();

    @Override
    public ProductDTO getProductById(String id) {
        if (!productMap.containsKey(id)) {
            throw new ProductNotFoundException("Product with ID " + id + " not found.");
        }
        System.out.println("Product Map" + productMap);
        return productMap.get(id);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productMap.values().stream().toList();
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        if (productDTO.getId() == null || productDTO.getId().isBlank()) {
            throw new InvalidProductException("Product ID must not be empty.");
        }
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            throw new InvalidProductException("Product name must not be empty.");
        }
        if (productMap.containsKey(productDTO.getId())) {
            throw new InvalidProductException("Product with ID " + productDTO.getId() + " already exists.");
        }
        productMap.put(productDTO.getId(), productDTO);
        return productDTO;
    }

    @Override
    public ProductDTO updateProduct(String id, ProductDTO productDTO) {
        if (!productMap.containsKey(id)) {
            throw new ProductNotFoundException("Cannot update. Product with ID " + id + " not found.");
        }
        if (productDTO.getId() == null) {
            throw new InvalidProductException("Product ID must not be null.");
        }
        if (productDTO.getName() == null || productDTO.getName().isBlank()) {
            throw new InvalidProductException("Product name must not be empty.");
        }

        productMap.put(id, productDTO);
        return productDTO;
    }

    @Override
    public void deleteProduct(String id) {
        if (!productMap.containsKey(id)) {
            throw new ProductNotFoundException("Cannot delete. Product with ID " + id + " not found.");
        }
        productMap.remove(id);
    }
}