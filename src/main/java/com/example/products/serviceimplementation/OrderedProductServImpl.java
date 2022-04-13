package com.example.products.serviceimplementation;

import com.example.products.entity.OrderedProducts;
import com.example.products.repository.OrderedProductsRepository;
import com.example.products.service.OrderedProductsService;
import org.springframework.stereotype.Service;

@Service
public class OrderedProductServImpl implements OrderedProductsService {

    private final OrderedProductsRepository orderedProductsRepo;

    public OrderedProductServImpl(OrderedProductsRepository orderedProductsRepo) {
        this.orderedProductsRepo = orderedProductsRepo;
    }

    @Override
    public OrderedProducts getOrderedProducts(long userId) {

        return orderedProductsRepo.findByUserId(userId).orElseThrow(() -> new RuntimeException("No Ordered Products"));
    }
}
