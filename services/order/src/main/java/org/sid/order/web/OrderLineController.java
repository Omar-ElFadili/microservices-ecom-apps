package org.sid.order.web;

import org.sid.order.dtos.OrderLineResponse;
import org.sid.order.services.OrderLineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
public class OrderLineController {

    private final OrderLineService orderLineService;
    public OrderLineController(OrderLineService orderLineService) {
        this.orderLineService = orderLineService;
    }

    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable(name = "order-id") Integer orderId) {

        return new ResponseEntity<>(orderLineService.getOrderLinesByOrderId(orderId), HttpStatus.OK);
    }
}
