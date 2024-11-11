package com.dreamcart.dreamshop.repository;

import com.dreamcart.dreamshop.model.Order;
import com.dreamcart.dreamshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserId(Long userId);
}
