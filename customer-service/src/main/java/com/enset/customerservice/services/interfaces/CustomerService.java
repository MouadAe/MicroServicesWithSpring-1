package com.enset.customerservice.services.interfaces;

import com.enset.customerservice.dtos.CustomerRequestDTO;
import com.enset.customerservice.dtos.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {
    CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO);
    CustomerResponseDTO getCustomer(String id);
    CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO);
    List<CustomerResponseDTO> getCustomers();
}
