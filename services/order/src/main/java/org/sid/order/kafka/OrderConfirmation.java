package org.sid.order.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.order.dtos.CustomerResponseDto;
import org.sid.order.dtos.PurchaseResponse;
import org.sid.order.entities.enums.PaymentMethod.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class OrderConfirmation {

    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private CustomerResponseDto customer;
    private List<PurchaseResponse> products;
}
