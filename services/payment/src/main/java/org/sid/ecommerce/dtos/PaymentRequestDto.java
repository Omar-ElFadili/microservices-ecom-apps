package org.sid.ecommerce.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.sid.ecommerce.entities.PaymentMethod.PaymentMethod;
import org.sid.ecommerce.models.Customer;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class PaymentRequestDto {
    private Integer id;
    private PaymentMethod paymentMethod;
    private Integer orderId;
    private BigDecimal amount;
    private String orderReference;
    private Customer customer;
}
