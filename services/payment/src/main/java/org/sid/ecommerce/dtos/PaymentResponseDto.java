package org.sid.ecommerce.dtos;

import lombok.*;
import org.sid.ecommerce.entities.PaymentMethod.PaymentMethod;
import org.sid.ecommerce.models.Customer;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResponseDto {
    private Integer id;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private BigDecimal amount;
    private String orderReference;
    private Customer customer;
}