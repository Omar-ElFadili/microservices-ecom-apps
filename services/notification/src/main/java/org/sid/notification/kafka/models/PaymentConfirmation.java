package org.sid.notification.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.sid.notification.enums.PaymentMethod;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PaymentConfirmation {
    private String orderReference;
    private BigDecimal amount;
    private PaymentMethod paymentMethod;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;

}
