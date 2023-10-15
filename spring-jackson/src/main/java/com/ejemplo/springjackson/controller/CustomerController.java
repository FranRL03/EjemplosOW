package com.ejemplo.springjackson.controller;

import com.ejemplo.springjackson.model.Customer;
import com.ejemplo.springjackson.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/")
    public List<Customer> findAll(){

        //return repository.findAll();
        return repository.findWithVehicles();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable long id){
        return repository.findById(id).orElseThrow();
    }
}
