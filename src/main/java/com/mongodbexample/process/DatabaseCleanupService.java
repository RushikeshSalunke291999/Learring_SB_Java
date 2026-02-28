package com.mongodbexample.process;

import com.mongodbexample.repository.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DatabaseCleanupService {

    @Autowired
    private productRepository productRepository;

    // Waits 7 days AFTER the last cleanup finished 
    @Scheduled(fixedDelay = 604800000)
    public void permanentDelete() {
        productRepository.deleteByIsDeletedTrue();
        System.out.println("Permanent cleanup of deleted products completed.");
    }
}