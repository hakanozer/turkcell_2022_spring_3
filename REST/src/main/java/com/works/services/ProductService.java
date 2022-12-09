package com.works.services;

import com.works.entities.Product;
import com.works.repositories.ProductRepository;
import com.works.utils.ERest;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ProductService {

    final ProductRepository productRepository;
    final CacheManager cacheManager;

    public ResponseEntity save(Product product) {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        productRepository.save(product);
        hm.put(ERest.status, true);
        hm.put(ERest.result, product);
        cacheManager.getCache("productList").clear();
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    public ResponseEntity list() {
        Map<ERest, Object> hm = new LinkedHashMap<>();
        hm.put(ERest.status, true);
        hm.put(ERest.result, productRepository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @Scheduled(fixedDelay = 150000)
    public void cacheClear() {
        System.out.println("cacheClear Call");
        cacheManager.getCache("productList").clear();
    }

}
