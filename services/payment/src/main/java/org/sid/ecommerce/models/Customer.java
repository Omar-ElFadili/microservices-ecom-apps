package org.sid.ecommerce.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Customer {
    private String id;
    @NotNull(message = "firstname is required")
    private String firstName;
    @NotNull(message = "lastname is required")
    private String lastName;
    @NotNull(message = "email is required")
    @Email(message = "the customer is not correctly formatted")
    private String email;

}
