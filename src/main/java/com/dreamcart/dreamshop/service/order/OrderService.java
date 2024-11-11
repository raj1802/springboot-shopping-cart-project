package com.dreamcart.dreamshop.service.order;

import com.dreamcart.dreamshop.dto.OrderDto;
import com.dreamcart.dreamshop.enums.OrderStatus;
import com.dreamcart.dreamshop.exception.ResourceNotFoundException;
import com.dreamcart.dreamshop.model.Cart;
import com.dreamcart.dreamshop.model.Order;
import com.dreamcart.dreamshop.model.OrderItem;
import com.dreamcart.dreamshop.model.Product;
import com.dreamcart.dreamshop.repository.CartRepository;
import com.dreamcart.dreamshop.repository.OrderRepository;
import com.dreamcart.dreamshop.repository.ProductRepository;
import com.dreamcart.dreamshop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService{
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final ModelMapper modelMapper;

    @Override
    public Order placeOrder(Long userId) {
        Cart  cart = cartService.getCartByUserId(userId);

        Order order =createOrder(cart);

        List<OrderItem> orderItemList = createOrderItems(order,cart);

        order.setOrderItems(new HashSet<>(orderItemList));
        order.setTotalAmount(calculateTotalAmount(orderItemList));

        Order savedOrder = orderRepository.save(order);

        cartService.clearCart(cart.getId());




        return savedOrder;
    }

private Order createOrder(Cart cart) {

        Order order = new Order();

        order.setUser(cart.getUser());


        //set the user
        order.setOrderStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());

        return order;

}


private List<OrderItem> createOrderItems(Order order, Cart cart) {

return cart.getItems().stream().map(cartItem -> {

    Product product = cartItem.getProduct();
    product.setInventory(product.getInventory() - cartItem.getQuantity());
    productRepository.save(product);

    return new OrderItem(

            order,
            product,
            cartItem.getQuantity(),
            cartItem.getUnitPrice());

}).toList();

    }

    private BigDecimal calculateTotalAmount(List<OrderItem> orderItemList) {

        return orderItemList
                .stream()
                .map(item -> item.getPrice().multiply(new BigDecimal(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

    }

    @Override
    public OrderDto getOrder(Long orderId) {
        return orderRepository.findById(orderId)
                .map(this :: convertToDto)
                .orElseThrow(() -> new ResourceNotFoundException("No orders found"));
    }

    @Override
    public List<OrderDto> getUserOrders(Long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return  orders.stream().map(this :: convertToDto).toList();
    }

    @Override
public OrderDto convertToDto(Order order)
{
    return modelMapper.map(order, OrderDto.class);
}



}
