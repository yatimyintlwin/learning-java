package com.handler.exception.controller;

import com.handler.exception.dto.ProductDTO;
import com.handler.exception.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        logger.info("Creating new product: {}", productDTO);
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable String id) {
        logger.info("Fetching product with ID: {}", id);
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        logger.info("Fetching all products");
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        logger.info("Updating product with ID: {}, Data: {}", id, productDTO);
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        logger.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        return "Product with ID " + id + " deleted successfully.";
    }
}
