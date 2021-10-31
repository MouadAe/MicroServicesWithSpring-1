package com.enset.customerservice.services;

import com.enset.customerservice.dtos.CustomerRequestDTO;
import com.enset.customerservice.dtos.CustomerResponseDTO;
import com.enset.customerservice.entities.Customer;
import com.enset.customerservice.mappers.CustomerMapper;
import com.enset.customerservice.repositories.CustomerRepository;
import com.enset.customerservice.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
//@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerResponseDTO save(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
//        customer.setId(UUID.randomUUID().toString());
        Optional<Customer> existing = customerRepository.findById(customer.getId());
        if(existing.isPresent()){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Customer already exist!");
        }
        Customer savedCustomer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(savedCustomer);
    }

    @Override
    public CustomerResponseDTO getCustomer(String id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Customer not found for this id : " + id)
                );
        return customerMapper.customerToCustomerResponseDTO(customer);
    }

    @Override
    public CustomerResponseDTO update(CustomerRequestDTO customerRequestDTO) {
        Customer customer = customerMapper.customerRequestDTOToCustomer(customerRequestDTO);
        Customer existing = customerRepository.findById(customer.getId())
                .orElseThrow( () ->
                        new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Customer not found!" )
        );
        Customer updatedCustomer =  customerRepository.save(customer);
        return customerMapper.customerToCustomerResponseDTO(updatedCustomer);
    }

    @Override
    public List<CustomerResponseDTO> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
//        if(customers.isEmpty()){
//            throw  new ResponseStatusException(HttpStatus.NO_CONTENT,"Customers list is empty!");
//        }
        return customers.stream()
                        .map( customer -> customerMapper.customerToCustomerResponseDTO((customer)) )
                        .collect(Collectors.toList());
    }
}
