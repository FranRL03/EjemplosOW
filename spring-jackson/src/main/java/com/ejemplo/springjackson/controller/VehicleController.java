package com.ejemplo.springjackson.controller;

import com.ejemplo.springjackson.model.Vehicle;
import com.ejemplo.springjackson.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository repository;

    @GetMapping("/")
    public List<Vehicle> findAll(){

        //return repository.findAll();
        return repository.findWithCustomers();
    }

    @GetMapping("/{id}")
    public Vehicle findById(@PathVariable long id){

        return repository.findById(id).orElseThrow();
    }


}
