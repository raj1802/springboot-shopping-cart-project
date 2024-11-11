package com.dreamcart.dreamshop.service.cart;


import com.dreamcart.dreamshop.exception.ResourceNotFoundException;
import com.dreamcart.dreamshop.model.Cart;
import com.dreamcart.dreamshop.model.CartItem;
import com.dreamcart.dreamshop.model.Product;
import com.dreamcart.dreamshop.repository.CartItemRepository;
import com.dreamcart.dreamshop.repository.CartRepository;
import com.dreamcart.dreamshop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor

public class CartItemService implements ICartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;
    private final IProductService productService;
    private final CartService cartService;


    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
        // Get the cart
        // Get the product
        // check if the product already in cart
        //if yes increase the quantity
        //if no, then initiate a new cartItem entry

        Cart  cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
                .stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst().orElse(new CartItem());

if(cartItem.getId()==null)
{
    cartItem.setProduct(product);
    cartItem.setQuantity(quantity);
    cartItem.setCart(cart);
    cartItem.setUnitPrice(product.getPrice());

}
else {
    cartItem.setQuantity(cartItem.getQuantity()+quantity);
}
cartItem.setTotalPrice();
cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = getCartItem(cartId, productId);


        cart.removeItem(itemToRemove);
        cartRepository.save(cart);


    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity) {

        Cart cart = cartService.getCart(cartId);
        cart.getItems()
                .stream()
                .filter(item->item.getProduct().getId().equals(productId))
                .findFirst()
                .ifPresent(item->{
                    item.setQuantity(quantity);
                    item.setUnitPrice(item.getProduct().getPrice());
                    item.setTotalPrice();

                });

        BigDecimal totalAmount = cart.getItems()
                .stream().map(CartItem ::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);


    }


    public CartItem getCartItem(Long cartId, Long productId) {

Cart cart = cartService.getCart(cartId);

return cart.getItems()
        .stream()
        .filter(item-> item.getProduct().getId().equals(productId))
        .findFirst().orElseThrow(()-> new ResourceNotFoundException("Item not found"));

    }
}
