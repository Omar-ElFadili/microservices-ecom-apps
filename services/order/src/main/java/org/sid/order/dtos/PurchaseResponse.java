package org.sid.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse {
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
