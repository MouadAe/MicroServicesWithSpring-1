package com.enset.billingservice.openfeign;

import com.enset.billingservice.entities.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name="CUSTOMER-SERVICE")
@RequestMapping("/api")
public interface CustomerRestClient {
    @GetMapping(path = "/customers/{id}")
    Customer getCustomer(@PathVariable String id);

    @GetMapping(path = "/customers")
    List<Customer> allCustomers();
}
