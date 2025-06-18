package com.app.product.controller;

import com.app.product.model.Product;
import com.app.product.service.ProductService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Product> create(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @GetMapping("/{pk}/{id}")
    public ResponseEntity<Product> getByPkAndId(@PathVariable String pk, @PathVariable String id) {
        return ResponseEntity.ok(productService.getProductByPkAndId(pk, id));
    }

    @PutMapping("/{pk}/{id}")
    public ResponseEntity<Product> update(@PathVariable String pk, @PathVariable String id, @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(pk, id, product));
    }

    @DeleteMapping("/{pk}/{id}")
    public ResponseEntity<String> delete(@PathVariable String pk, @PathVariable String id) {
        productService.deleteProduct(pk, id);
        return ResponseEntity.ok("Product with pk " + pk + " and ID " + id + " was deleted successfully.");
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.getAllProducts());
    }
}