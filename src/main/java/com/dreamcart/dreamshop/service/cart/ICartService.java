package com.dreamcart.dreamshop.service.cart;

import com.dreamcart.dreamshop.model.Cart;
import com.dreamcart.dreamshop.model.User;

import java.math.BigDecimal;

public interface ICartService {

    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);


    Cart initializeNewCart(User user);



    Cart getCartByUserId(Long userId);
}
