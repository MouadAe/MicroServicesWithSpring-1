package com.enset.customerservice;

import com.enset.customerservice.dtos.CustomerRequestDTO;
import com.enset.customerservice.entities.Customer;
import com.enset.customerservice.repositories.CustomerRepository;
import com.enset.customerservice.services.interfaces.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerService customerService){
        return args -> {
            customerService.save(new CustomerRequestDTO("C01","cust1","cust1@gmail.com"));
            customerService.save(new CustomerRequestDTO("C02","cust2","cust2@gmail.com"));
//            customerRepository.save(new Customer("C01","cust1","cust1@gmail.com"));
//            customerRepository.save(new Customer("C02","cust2","cust2@gmail.com"));
        };
    }
}
