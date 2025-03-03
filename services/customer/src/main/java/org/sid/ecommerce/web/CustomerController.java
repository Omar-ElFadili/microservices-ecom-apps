package org.sid.ecommerce.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sid.ecommerce.documents.Customer;
import org.sid.ecommerce.dtos.CustomerRequestDto.CustomerRequestDto;
import org.sid.ecommerce.dtos.CustomerResponseDto.CustomerResponseDto;
import org.sid.ecommerce.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid CustomerRequestDto customer) {
        return new ResponseEntity<>(customerService.createNewCustomer(customer), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequestDto customer) {
        customerService.updateCustomer(customer);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseDto>> getCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponseDto> getCustomer(@PathVariable("customer-id") String customerId) {
        CustomerResponseDto customerResponseDto = customerService.getCustomerById(customerId);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.OK);
    }

    @GetMapping("exist/{customer-id}")
    public ResponseEntity<Boolean> isExistingCustomer(@PathVariable("customer-id") Long customerId) {
        return ResponseEntity.ok(customerService.isCustomerExist(customerId));
    }

    @DeleteMapping("{customer-id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable("customer-id") Long customerId) {
        customerService.deletingCustomer(customerId);
        return ResponseEntity.accepted().build();
    }

}
