package com.dreamcart.dreamshop.service.order;

import com.dreamcart.dreamshop.dto.OrderDto;
import com.dreamcart.dreamshop.model.Order;

import java.util.List;



public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);


  List<OrderDto> getUserOrders(Long userId);

    OrderDto convertToDto(Order order);

}
