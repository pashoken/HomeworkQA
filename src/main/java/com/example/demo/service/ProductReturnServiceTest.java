package com.example.demo.service;

import com.example.demo.exception.BadProductReturnCountException;
import com.example.demo.exception.EntityNotFoundException;
import com.example.demo.model.Order;
import com.example.demo.model.ProductReturn;
import com.example.demo.repository.ProductReturnRepository;
import com.example.demo.service.ProductReturnService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductReturnServiceTest {

    @Mock
    private ProductReturnRepository productReturnRepository;

    @InjectMocks
    private ProductReturnService productReturnService;

    @Test
    public void testAdd_Success() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setQuantity(10);

        productReturnService.add(order, 5);

        verify(productReturnRepository, times(1)).save(any(ProductReturn.class));
    }

    @Test
    public void testAdd_BadProductReturnCountException() {
        Order order = new Order();
        order.setId(UUID.randomUUID());
        order.setQuantity(10);

        assertThrows(BadProductReturnCountException.class, () -> {
            productReturnService.add(order, 15);
        });
    }

    @Test
    public void testFindAll() {
        List<ProductReturn> productReturns = List.of(new ProductReturn(), new ProductReturn());
        when(productReturnRepository.findAll()).thenReturn(productReturns);

        List<ProductReturn> result = productReturnService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindById_Success() {
        ProductReturn productReturn = new ProductReturn();
        productReturn.setId(UUID.randomUUID());
        when(productReturnRepository.findById(any(UUID.class))).thenReturn(Optional.of(productReturn));

        ProductReturn result = productReturnService.findById(productReturn.getId());

        assertEquals(productReturn.getId(), result.getId());
    }

    @Test
    public void testFindById_EntityNotFoundException() {
        UUID id = UUID.randomUUID();
        when(productReturnRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            productReturnService.findById(id);
        });
    }
}