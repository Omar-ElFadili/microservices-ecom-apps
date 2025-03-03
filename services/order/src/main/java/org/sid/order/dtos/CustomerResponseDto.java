package org.sid.order.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerResponseDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
}
