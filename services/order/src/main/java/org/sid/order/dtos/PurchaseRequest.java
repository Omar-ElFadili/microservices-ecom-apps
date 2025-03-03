package org.sid.order.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseRequest {

    @NotNull(message = "product ID is mandatory")
    private Integer productId;
    @Positive(message = "quantity is mandatory")
    private double quantity;
}
