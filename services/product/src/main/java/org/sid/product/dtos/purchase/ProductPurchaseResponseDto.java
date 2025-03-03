package org.sid.product.dtos.purchase;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseResponseDto {
    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;

}
