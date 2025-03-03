package org.sid.product.dtos;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ProductRequestDto {
        @NotNull(message = "product name is required")
        String name;
        @NotNull(message = "product desc is required")
        String description;
        @Positive(message = "product available quantity must be positive number")
        double availableQuantity;
        @NotNull(message = "product price is required")
        BigDecimal price;
        Integer categoryId;
}
