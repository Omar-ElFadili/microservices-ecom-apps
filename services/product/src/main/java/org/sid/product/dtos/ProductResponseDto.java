package org.sid.product.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.sid.product.entities.Category;

import java.math.BigDecimal;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseDto {

    String name;
    String description;
    double availableQuantity;
    BigDecimal price;
    Category category;
}
