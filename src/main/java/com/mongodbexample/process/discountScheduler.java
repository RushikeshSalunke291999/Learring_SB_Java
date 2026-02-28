package com.mongodbexample.process;

import com.mongodbexample.model.Product;
import com.mongodbexample.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class discountScheduler {

    @Autowired
    private productRepository productRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public List<Product> midNightDiscount(){
        List<Product> products = productRepository.findByCategory("electronics");
        for(Product p:products){
            double newPrice = p.getPrice() * 0.90; // 10% off
            p.setPrice((int)newPrice);
            productRepository.save(p);
        }
        System.out.println("Midnight discount applied to eletronics category.");
        return products;
    }
}
