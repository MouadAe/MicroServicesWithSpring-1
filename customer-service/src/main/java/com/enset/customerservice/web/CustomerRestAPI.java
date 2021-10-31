package com.enset.customerservice.web;

import com.enset.customerservice.dtos.CustomerRequestDTO;
import com.enset.customerservice.dtos.CustomerResponseDTO;
import com.enset.customerservice.services.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CustomerRestAPI{
        private CustomerService customerService;

        // 1 save customer
        // 2 get customer
        // 3 update customer
        // 4 getAll customers

        @PostMapping("/addCustomer")
        public CustomerResponseDTO save(@RequestBody CustomerRequestDTO customerRequestDTO){
            return customerService.save(customerRequestDTO);
        }

        @GetMapping("customers/{customerId}")
        public CustomerResponseDTO getCustomer(@PathVariable String customerId){
            return customerService.getCustomer(customerId);
        }

        @PutMapping("/updateCustomer")
        public CustomerResponseDTO updateCustomer(@RequestBody CustomerRequestDTO customerRequestDTO){
            return customerService.update(customerRequestDTO);
        }

        @GetMapping(path = "/customers")
        public List<CustomerResponseDTO> getAllCustomers(){
            return customerService.getCustomers();
        }
}
