package com.ejemplo.springjackson.repository;

import com.ejemplo.springjackson.model.Customer;
import com.ejemplo.springjackson.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    @Query("select distinct v " +
            "from Vehicles v " +
            "   join fetch v.customers")
        List<Vehicle> findWithCustomers();
}
