package org.sid.product.dtos.purchase;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequestDto {

        @NotNull(message = "product id is mandatory")
        Integer productId;
        @NotNull(message = "product name is mandatory")
        String name;
        @NotNull(message = "product quantity id is mandatory")
        double quantity;

}
