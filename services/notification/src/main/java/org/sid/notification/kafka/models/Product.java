package org.sid.notification.kafka.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Product {

    private Integer productId;
    private String name;
    private String description;
    private BigDecimal price;
    private double quantity;
}
