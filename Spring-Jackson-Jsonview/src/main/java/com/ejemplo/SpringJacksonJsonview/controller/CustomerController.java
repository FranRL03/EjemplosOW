package com.ejemplo.SpringJacksonJsonview.controller;

import com.ejemplo.SpringJacksonJsonview.model.Customer;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ejemplo.SpringJacksonJsonview.model.CustomerView.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private List<Customer> customers;

    public CustomerController(){
        this.customers = List.of(
                new Customer(1L, "cust1", "cust1@gmail.com", 4000.0, "admin", "1234", 2021, true),
                new Customer(2L, "cust2", "cust2@gmail.com", 4000.0, "admin", "1234", 2021, true),
                new Customer(3L, "cust3", "cust3@gmail.com", 4000.0, "admin", "1234", 2021, true)
        );
    }

    @GetMapping("/")
    @JsonView(CustomerList.class)
    public List<Customer> findAll(){
        return customers;
    }

    @GetMapping("/{id}")
    @JsonView(CustomerDetail.class)
    public Customer findById(@PathVariable Long id){
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @GetMapping("/edit/{id}")
    @JsonView(CustomerEdit.class)
    public Customer findByIdForEdit(@PathVariable long id){
        return customers.stream()
                .filter(customer -> customer.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
