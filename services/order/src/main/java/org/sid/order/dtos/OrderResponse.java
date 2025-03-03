package org.sid.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.sid.order.entities.OrderLine;
import org.sid.order.entities.enums.PaymentMethod.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String reference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private String customerId;
    private List<OrderLine> orderLines;
}
