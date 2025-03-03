package org.sid.order.feign.requests;

import lombok.Builder;
import org.sid.order.dtos.CustomerResponseDto;
import org.sid.order.entities.enums.PaymentMethod.PaymentMethod;

import java.math.BigDecimal;

@Builder
public class PaymentPaymentRequest {
    private Integer id;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private BigDecimal amount;
    private String orderReference;
    private CustomerResponseDto customer;
}
