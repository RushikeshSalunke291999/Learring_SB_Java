package com.mongodbexample.repository;

import com.mongodbexample.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface productRepository extends MongoRepository<Product, String> {

    @Query("{ 'category' : ?0 }")
    List<Product> findByCategory(String category);
}
