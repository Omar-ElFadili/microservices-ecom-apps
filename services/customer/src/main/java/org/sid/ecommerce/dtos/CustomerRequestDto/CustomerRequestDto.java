package org.sid.ecommerce.dtos.CustomerRequestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.sid.ecommerce.documents.Address;

public record CustomerRequestDto (
        String id,
        @NotNull(message = "firstname can not be null")
        String firstName,
        @NotNull(message = "lastname can not be null")
        String lastName,
        @NotNull(message = "email can not be null")
        @Email(message = "this is not a valid email")
        String email,
        Address address
){

}
