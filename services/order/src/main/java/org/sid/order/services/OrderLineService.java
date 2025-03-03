package org.sid.order.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.sid.order.dtos.OrderLineMapper;
import org.sid.order.dtos.OrderLineRequest;
import org.sid.order.dtos.OrderLineResponse;
import org.sid.order.entities.OrderLine;
import org.sid.order.repositories.OrderLineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class OrderLineService {

    private OrderLineRepository orderLineRepository;
    private OrderLineMapper orderLineMapper;

    public OrderLineService(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    public Integer createOrderLine(OrderLineRequest orderLineRequest) {
        OrderLine orderLine = orderLineMapper.toEntity(orderLineRequest);
        return orderLineRepository.save(orderLine).getId();
    }

    public List<OrderLineResponse> getOrderLinesByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toDto)
                .toList();
    }
}
