package org.sid.ecommerce.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sid.ecommerce.entities.PaymentMethod.PaymentMethod;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PaymentNotificationRequest {

    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
}
