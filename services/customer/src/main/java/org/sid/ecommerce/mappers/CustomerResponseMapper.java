package org.sid.ecommerce.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.ecommerce.documents.Customer;
import org.sid.ecommerce.dtos.CustomerRequestDto.CustomerRequestDto;
import org.sid.ecommerce.dtos.CustomerResponseDto.CustomerResponseDto;

@Mapper
public interface CustomerResponseMapper {

    CustomerResponseMapper INSTANCE = Mappers.getMapper(CustomerResponseMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    CustomerResponseDto toDTO(Customer customer);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    Customer toEntity(CustomerResponseDto userDTO);
}