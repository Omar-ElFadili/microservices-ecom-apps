package org.sid.product.mappers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.sid.product.dtos.purchase.ProductPurchaseRequestDto;
import org.sid.product.dtos.purchase.ProductPurchaseResponseDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProductPurchaseMapper {

    public ProductPurchaseMapper() {
    }
    public ProductPurchaseResponseDto toRequest(ProductPurchaseRequestDto product){
        return new ProductPurchaseResponseDto(product.getProductId(), product.getName(), null, null, product.getQuantity());
    }

}
