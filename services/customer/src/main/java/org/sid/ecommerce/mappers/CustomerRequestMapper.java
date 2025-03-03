package org.sid.ecommerce.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.sid.ecommerce.documents.Customer;
import org.sid.ecommerce.dtos.CustomerRequestDto.CustomerRequestDto;

@Mapper
public interface CustomerRequestMapper {

    CustomerRequestMapper INSTANCE = Mappers.getMapper(CustomerRequestMapper.class);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    CustomerRequestDto toDTO(Customer customer);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    Customer toEntity(CustomerRequestDto userDTO);
}
