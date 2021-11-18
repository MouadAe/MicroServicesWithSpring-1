package com.enset.customerservice.repositories;

import com.enset.customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource   // automatically create endpoints
public interface CustomerRepository extends JpaRepository<Customer,String> {
}
