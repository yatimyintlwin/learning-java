package com.handler.exception.controller;

import com.handler.exception.dto.ProductDTO;
import com.handler.exception.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductDTO productDTO) {
        return productService.createProduct(productDTO);
    }

    @GetMapping("/{id}")
    public ProductDTO getById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }

    @PutMapping("/{id}")
    public ProductDTO update(@PathVariable String id, @RequestBody ProductDTO productDTO) {
        return productService.updateProduct(id, productDTO);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        productService.deleteProduct(id);
        return "Product with ID " + id + " deleted successfully.";
    }
}

