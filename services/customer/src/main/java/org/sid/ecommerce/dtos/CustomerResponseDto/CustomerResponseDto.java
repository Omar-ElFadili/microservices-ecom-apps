package org.sid.ecommerce.dtos.CustomerResponseDto;

import org.sid.ecommerce.documents.Address;

public record CustomerResponseDto(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
