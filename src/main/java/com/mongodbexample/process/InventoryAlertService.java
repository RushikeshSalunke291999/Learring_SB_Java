package com.mongodbexample.process;

import com.mongodbexample.exception.ProductSyncException;
import com.mongodbexample.model.Product;
import com.mongodbexample.repository.productRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryAlertService {

    private static final Logger logger = LoggerFactory.getLogger(InventoryAlertService.class);

    @Autowired
   private productRepository productRepository;

    @Scheduled(fixedRate = 1000)
    public void alertLowStock() {
        List<Product> lowStock = productRepository.findByQtyLessThan(5);
        try{
            if (!lowStock.isEmpty()) {
                logger.error("ALERT: {} products are low on stock!", lowStock.size());
            }
        }catch (ProductSyncException e){
            logger.error("Error while getting low stock products", e);
            throw new ProductSyncException("Error while getting low stock products");
        }
    }
    public List<Product> getLowStockProducts(int threshold) {
        try {
            return productRepository.findByQtyLessThan(threshold);
        } catch (Exception e) {
            logger.error("Error while fetching low stock products", e);
            throw new ProductSyncException("Error while fetching low stock products");
        }
    }
}