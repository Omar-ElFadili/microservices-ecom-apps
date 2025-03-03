package org.sid.order.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class OrderRequestDto {

    private Integer id;
    private String reference;
    @Positive(message = "order amount should be positive")
    private BigDecimal totalAmount;
    @NotNull(message = "payment method can not be null")
    private PaymentMethod paymentMethod;
    @NotEmpty(message = "order amount should be present")
    @NotBlank(message = "order amount should be present")
    @NotNull(message = "order amount should be present ")
    private String customerId;
    @NotEmpty(message = "you should at least purchase one product")
    private List<PurchaseRequest> products;
}
