package org.sid.order.dtos;

import org.sid.order.entities.Order;
import org.sid.order.entities.OrderLine;

public class OrderLineMapper {

    public OrderLine toEntity(OrderLineRequest orderLineRequest) {
        return OrderLine.builder()
                .id(orderLineRequest.getId())
                .productId(orderLineRequest.getProductId())
                .quantity(orderLineRequest.getQuantity())
                .order(
                        Order.builder()
                                .id(orderLineRequest.getOrderId())
                                .build()
                )
                .build();
    }

    public OrderLineResponse toDto(OrderLine orderLine) {
        OrderLineResponse orderLineResponse = new OrderLineResponse();
        orderLineResponse.setId(orderLine.getId());
        orderLineResponse.setQuantity(orderLine.getQuantity());
        return orderLineResponse;
    }
}
