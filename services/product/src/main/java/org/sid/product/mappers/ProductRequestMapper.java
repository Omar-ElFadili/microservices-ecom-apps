package org.sid.product.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.product.dtos.ProductRequestDto;
import org.sid.product.entities.Product;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {


    ProductRequestDto toDTO(Product product);

    Product toEntity(ProductRequestDto productRequestDto);
}
