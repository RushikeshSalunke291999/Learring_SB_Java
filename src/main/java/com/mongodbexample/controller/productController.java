package com.mongodbexample.controller;

import com.mongodbexample.model.Product;
import com.mongodbexample.process.InventoryAlertService;
import com.mongodbexample.process.discountScheduler;
import com.mongodbexample.repository.productRepository;
import com.mongodbexample.servive.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
@RestController
@RequestMapping("/api")
public class productController {
    @Autowired
    private productRepository productRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private discountScheduler discountScheduler;

    @Autowired
    private InventoryAlertService inventoryAlertService;

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

    @GetMapping("/DiscountedProducts")
    public ResponseEntity<List<Product>> getDiscountedProducts() {
        return ResponseEntity.ok(discountScheduler.midNightDiscount());
    }

    @GetMapping("/InventoryAlert")
    public ResponseEntity<List<Product>> getInventoryAlert() {
        return ResponseEntity.ok(inventoryAlertService.getLowStockProducts(5));
    }
}
