package org.sid.notification.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sid.notification.enums.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private Customer customer;
    private List<Product> products;
}
