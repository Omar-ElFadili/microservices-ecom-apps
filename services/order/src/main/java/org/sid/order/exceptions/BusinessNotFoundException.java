package org.sid.order.exceptions;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class BusinessNotFoundException extends RuntimeException {

    public BusinessNotFoundException(String message) {
        super(message);
    }
}
