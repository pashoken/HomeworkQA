package com.example.demo.controller;

import com.example.demo.model.ProductReturn;
import com.example.demo.service.ProductReturnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product-returns")
public class ProductReturnController {

    @Autowired
    private ProductReturnService productReturnService;

    @GetMapping
    public List<ProductReturn> getAll() {
        return productReturnService.findAll();
    }

    @GetMapping("/{id}")
    public ProductReturn getById(@PathVariable UUID id) {
        return productReturnService.findById(id);
    }
}