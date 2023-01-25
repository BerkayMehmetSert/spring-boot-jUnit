package com.bms.springboottest.controller;

import com.bms.springboottest.dto.CategoryDto;
import com.bms.springboottest.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createCategory(@RequestParam String name) {
        service.createCategory(name);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable String id, @RequestParam String name) {
        service.updateCategory(id, name);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id) {
        service.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> findCategoryById(@PathVariable String id) {
        return ResponseEntity.ok(service.findCategoryById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<CategoryDto> findCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(service.findCategoryByName(name));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> findAllCategories() {
        return ResponseEntity.ok(service.findAllCategories());
    }
}
