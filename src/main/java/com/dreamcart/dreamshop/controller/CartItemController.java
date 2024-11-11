package com.dreamcart.dreamshop.controller;

import com.dreamcart.dreamshop.exception.ResourceNotFoundException;
import com.dreamcart.dreamshop.model.Cart;
import com.dreamcart.dreamshop.model.User;
import com.dreamcart.dreamshop.response.ApiResponse;
import com.dreamcart.dreamshop.service.cart.ICartItemService;
import com.dreamcart.dreamshop.service.cart.ICartService;
import com.dreamcart.dreamshop.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;



@PostMapping("/item/add")
 public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId, @RequestParam Integer quantity) {


     try {

         User user = userService.getUserById(4L);
         Cart cart = cartService.initializeNewCart(user);


         cartItemService.addItemToCart(cart.getId(), productId, quantity);
         return ResponseEntity.ok(new ApiResponse("Item added successfully",null));
     } catch (Exception e) {
         return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
     }

 }



    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        try {
            cartItemService.removeItemFromCart(cartId, itemId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                           @PathVariable Long itemId,
                                                           @RequestParam Integer quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, itemId, quantity);
            return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }

    }


}
