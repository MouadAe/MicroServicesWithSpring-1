package com.enset.customerservice.mappers;

import com.enset.customerservice.dtos.CustomerRequestDTO;
import com.enset.customerservice.dtos.CustomerResponseDTO;
import com.enset.customerservice.entities.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerResponseDTO customerToCustomerResponseDTO(Customer customer);
    Customer customerRequestDTOToCustomer(CustomerRequestDTO customerRequestDTO);
}
