package org.sid.order.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineRequest {

    private Integer id;
    private Integer orderId;
    private Integer productId;
    private double quantity;
}
