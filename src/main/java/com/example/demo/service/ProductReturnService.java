package com.example.demo.service;

import com.example.demo.model.ProductReturn;
import com.example.demo.model.Order;
import com.example.demo.repository.ProductReturnRepository;
import com.example.demo.exception.BadProductReturnCountException;
import com.example.demo.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ProductReturnService {

    @Autowired
    private ProductReturnRepository productReturnRepository;

    public void add(Order order, long count) {
        if (count > order.getQuantity()) {
            throw new BadProductReturnCountException("Return count exceeds order quantity");
        }

        ProductReturn productReturn = new ProductReturn();
        productReturn.setId(UUID.randomUUID());
        productReturn.setOrderId(order.getId());
        productReturn.setDate(LocalDate.now());
        productReturn.setQuantity((int) count);

        productReturnRepository.save(productReturn);
    }

    public List<ProductReturn> findAll() {
        return productReturnRepository.findAll();
    }

    public ProductReturn findById(UUID id) {
        return productReturnRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ProductReturn not found"));
    }
}