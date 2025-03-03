package org.sid.product.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.sid.product.dtos.ProductRequestDto;
import org.sid.product.dtos.ProductResponseDto;
import org.sid.product.entities.Product;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface ProductResponseMapper {


    ProductResponseDto toDTO(Product product);

    Product toEntity(ProductResponseDto productResponseDto);
}
