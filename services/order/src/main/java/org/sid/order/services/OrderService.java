package org.sid.order.services;

import org.sid.order.dtos.*;
import org.sid.order.entities.Order;
import org.sid.order.exceptions.BusinessNotFoundException;
import org.sid.order.feign.CustomerClient;
import org.sid.order.feign.PaymentClient;
import org.sid.order.feign.requests.PaymentPaymentRequest;
import org.sid.order.kafka.OrderConfirmation;
import org.sid.order.kafka.OrderProducer;
import org.sid.order.mappers.OrderMapper;
import org.sid.order.repositories.OrderRepository;
import org.sid.order.templates.ProductClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public OrderService(OrderRepository orderRepository, CustomerClient customerClient,
                        ProductClient productClient, OrderMapper orderMapper,
                        OrderLineService orderLineService, OrderProducer orderProducer,
                        PaymentClient paymentClient) {
        this.orderRepository = orderRepository;
        this.customerClient = customerClient;
        this.productClient = productClient;
        this.orderMapper = orderMapper;
        this.orderLineService = orderLineService;
        this.orderProducer = orderProducer;
        this.paymentClient = paymentClient;
    }

    public Integer createOrder(OrderRequestDto orderRequestDto) {
        //check the customer
        CustomerResponseDto customerResponseDto = customerClient.getCustomerById(orderRequestDto.getCustomerId())
                .orElseThrow(() -> new BusinessNotFoundException("customer does not exist with this id : "+orderRequestDto.getCustomerId()));

        //purchase the products --> products-ms
        List<PurchaseResponse> purchasedProducts = productClient.purchaseProducts(orderRequestDto.getProducts());

        //persist order
        Order order = orderRepository.save(orderMapper.toEntity(orderRequestDto));

        //persist order lines
        for (PurchaseRequest purchaseRequest : orderRequestDto.getProducts()){
            OrderLineRequest orderLineRequest = new OrderLineRequest(
                    null, order.getId(), purchaseRequest.getProductId(), purchaseRequest.getQuantity()
            );
            orderLineService.createOrderLine(orderLineRequest);
        }

        // todo start payment
        PaymentPaymentRequest paymentPaymentRequest = PaymentPaymentRequest.builder()
                .id(orderRequestDto.getId())
                .orderReference(orderRequestDto.getReference())
                .paymentMethod(orderRequestDto.getPaymentMethod())
                .amount(orderRequestDto.getTotalAmount())
                .customer(customerResponseDto)
                .build();
        paymentClient.requestOrderPayment(paymentPaymentRequest);

        //send the order confirmation --> notification-ms (KAFKA)
        OrderConfirmation orderConfirmation = new OrderConfirmation(
                orderRequestDto.getReference(),
                orderRequestDto.getTotalAmount(),
                orderRequestDto.getPaymentMethod(),
                customerResponseDto,
                purchasedProducts
        );
        orderProducer.sendOrderConfirmation(orderConfirmation);

        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    public OrderResponse findOrderById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .orElseThrow(() -> new BusinessNotFoundException("order does not exist with this id : "+orderId));
    }
}
