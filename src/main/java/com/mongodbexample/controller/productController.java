package com.mongodbexample.controller;

import com.mongodbexample.model.Product;
import com.mongodbexample.servive.productService;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class productController {

    @Resource
    private productService productService;

    @GetMapping("/products")
    public String syncProducts() {
        productService.fetchAndSaveProducts();
        return "Sync Started!";
    }

    @GetMapping("/AllProduct")
    public ResponseEntity<List<Product>> getAllProduct() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("/ProductByCategory/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        return ResponseEntity.ok(productService.getProductCategory(category));
    }

    @GetMapping("/ProductById/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }
}
