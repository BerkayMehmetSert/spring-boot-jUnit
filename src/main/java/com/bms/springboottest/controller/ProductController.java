package com.bms.springboottest.controller;

import com.bms.springboottest.dto.ProductDto;
import com.bms.springboottest.request.CreateProductRequest;
import com.bms.springboottest.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody CreateProductRequest request) {
        service.createProduct(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/name")
    public ResponseEntity<Void> updateProductName(@PathVariable String id, @RequestParam String name) {
        service.updateProductName(id, name);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<Void> updateProductPrice(@PathVariable String id, @RequestParam double price) {
        service.updateProductPrice(id, price);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/category")
    public ResponseEntity<Void> updateProductCategory(@PathVariable String id, @RequestParam String categoryId) {
        service.updateProductCategory(id, categoryId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        service.deleteProduct(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable String id) {
        return ResponseEntity.ok(service.findProductById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDto> findProductByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findProductByName(name));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<ProductDto> findProductByCategory(@PathVariable String categoryId) {
        return ResponseEntity.ok(service.findProductByCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> findAllProducts() {
        return ResponseEntity.ok(service.findAllProducts());
    }
}
