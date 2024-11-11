package com.dreamcart.dreamshop.repository;

import com.dreamcart.dreamshop.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository  extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
    List<CartItem> findByProductId(Long productId);
}
