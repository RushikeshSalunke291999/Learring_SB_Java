package com.mongodbexample.servive;

import com.mongodbexample.exception.ProductSyncException;
import com.mongodbexample.model.Product;
import com.mongodbexample.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.List;

@Service
public class ProductService {

    private final productRepository productRepository;
    private final WebClient webClient;

    private static final Logger logger = LoggerFactory.getLogger(Product.class);

    public ProductService(productRepository productRepository, WebClient.Builder webClient) {
        this.productRepository = productRepository;
        this.webClient = WebClient.builder().baseUrl("https://fakestoreapi.com").build();
    }

    public void fetchAndSaveProducts() {
        try {
            List<Product> allProduct = webClient.get()
                    .uri("/products")
                    .retrieve()
                    .bodyToFlux(Product.class)
                    .collectList()
                    .block();

            if (allProduct != null) {
                productRepository.saveAll(allProduct);
                logger.debug("Fetched all {} products", allProduct.size());
            }
        } catch (Exception e) {
            throw new ProductSyncException("Error while fetching products" + e.getMessage());
        }
    }

    public List<Product> getAllProduct() {
        try {
            return productRepository.findAll();
        } catch (Exception e) {
            throw new ProductSyncException("Error while getting All products list" + e.getMessage());
        }
    }

    public List<Product> getProductCategory(String category) {
        try {
            if (category == null || category.trim().isEmpty()) {
                throw new ProductSyncException("Category is required");
            }
            String normalizedCategory = category.toLowerCase().trim();
            List<Product> byCategory = productRepository.findByCategory(normalizedCategory);

            if (byCategory == null && byCategory.isEmpty()) {
                throw new ProductSyncException("No products found for category " + category);
            }
        } catch (ProductSyncException e) {
            throw e;
        } catch (Exception e) {
            throw new ProductSyncException("Error while getting products by category" + e.getMessage());
        }
        return productRepository.findByCategory(category);
    }

    public Product getProductById(String id) {
        try {
            if(id == null || id.trim().isEmpty()){
                throw new ProductSyncException("Product Id is required");
            }
            return productRepository.findById(id).orElse(null);
        }catch(Exception e){
            throw new ProductSyncException("Error while getting product by id" + e.getMessage());
        }
    }
}
