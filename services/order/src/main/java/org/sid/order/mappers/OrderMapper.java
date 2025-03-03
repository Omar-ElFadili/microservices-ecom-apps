package org.sid.order.mappers;

import org.sid.order.dtos.OrderRequestDto;
import org.sid.order.dtos.OrderResponse;
import org.sid.order.entities.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toEntity(OrderRequestDto orderRequestDto) {
        Order order = Order.builder()
                .id(orderRequestDto.getId())
                .paymentMethod(orderRequestDto.getPaymentMethod())
                .totalAmount(orderRequestDto.getTotalAmount())
                .reference(orderRequestDto.getReference())
                .build();
        return order;
    }
    public OrderResponse toDto(Order order) {
        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setPaymentMethod(order.getPaymentMethod());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setReference(order.getReference());
        return orderResponse;
    }
}
