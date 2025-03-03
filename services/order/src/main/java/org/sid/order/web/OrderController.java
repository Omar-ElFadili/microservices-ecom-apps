package org.sid.order.web;

import jakarta.validation.Valid;
import org.sid.order.dtos.OrderRequestDto;
import org.sid.order.dtos.OrderResponse;
import org.sid.order.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private OrderService orderService;
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequestDto orderRequestDto) {
        Integer result = orderService.createOrder(orderRequestDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAllOrders(){
        return new ResponseEntity<>(orderService.findAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable(name = "order-id") Integer orderId){
        OrderResponse orderResponse = orderService.findOrderById(orderId);
        return new ResponseEntity<>(orderResponse, HttpStatus.OK);
    }
}
