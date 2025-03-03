package org.sid.order.feign;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.sid.order.dtos.CustomerResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "customer-service",
        url = "${application.config.customer-url}"
)
public interface CustomerClient {

    @GetMapping("/{customer-id}")
    @CircuitBreaker(name = "customerServiceCB", fallbackMethod = "getDefaultCustomer")
    Optional<CustomerResponseDto> getCustomerById(@PathVariable("customer-id") String id);

    default Optional<CustomerResponseDto> getDefaultCustomer(String id, Exception exception) {
        CustomerResponseDto customerResponseDto = CustomerResponseDto.builder()
                .id(id)
                .email("default email")
                .firstName("default first name")
                .lastName("default last name")
                .build();
        return Optional.of(customerResponseDto);
    }
}
