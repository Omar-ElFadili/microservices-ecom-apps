package org.sid.ecommerce.services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.mapstruct.factory.Mappers;
import org.sid.ecommerce.documents.Customer;
import org.sid.ecommerce.dtos.CustomerRequestDto.CustomerRequestDto;
import org.sid.ecommerce.dtos.CustomerResponseDto.CustomerResponseDto;
import org.sid.ecommerce.exceptions.CustomerNotFoundException;
import org.sid.ecommerce.mappers.CustomerRequestMapper;
import org.sid.ecommerce.mappers.CustomerResponseMapper;
import org.sid.ecommerce.repositories.CustomerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;
    private final CustomerRequestMapper customerRequestMapper = Mappers.getMapper(CustomerRequestMapper.class);
    private final CustomerResponseMapper customerResponseMapper = Mappers.getMapper(CustomerResponseMapper.class);

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createNewCustomer(CustomerRequestDto customerRequestDto) {
        Customer customer = customerRequestMapper.toEntity(customerRequestDto);
        return customerRepository.save(customer);
    }

    public void updateCustomer(CustomerRequestDto customer) {
        Customer customerToUpdate = customerRepository.findById(customer.id())
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id %s not found"));
        mergeCustomer(customerToUpdate, customer);
        customerRepository.save(customerToUpdate);
    }

    private void mergeCustomer(Customer customerToUpdate, CustomerRequestDto customer) {
        if(StringUtils.isNotBlank(customer.firstName())) {
            customerToUpdate.setFirstName(customer.firstName());
        }
        if(StringUtils.isNotBlank(customer.lastName())) {
            customerToUpdate.setLastName(customer.lastName());
        }
        if(StringUtils.isNotBlank(customer.email())) {
            customerToUpdate.setEmail(customer.email());
        }
        if(StringUtils.isNotBlank(String.valueOf(customer.address()))){
            customerToUpdate.setAddress(customer.address());
        }
    }

    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerResponseDto customerResponseDto = customerResponseMapper.toDTO(customer);
            customerResponseDtos.add(customerResponseDto);
        });
        return ResponseEntity.ok(customerResponseDtos);
    }

    public CustomerResponseDto getCustomerById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerResponseMapper::toDTO)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with id %s not found", customerId)
                ));
    }

    public boolean isCustomerExist(Long customerId) {
        return customerRepository.findById(customerId.toString()).isPresent();
    }

    public void deletingCustomer(Long customerId) {
        boolean isCustomerExist = isCustomerExist(customerId);
        if(isCustomerExist){
            customerRepository.deleteById(customerId.toString());
        }
    }
}
