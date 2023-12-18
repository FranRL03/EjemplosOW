package com.jpa.criteria.springdatajpacriteria.repository;

import com.jpa.criteria.springdatajpacriteria.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
