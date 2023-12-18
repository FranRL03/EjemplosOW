package com.jpa.criteria.springdatajpacriteria.repository;

import com.jpa.criteria.springdatajpacriteria.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.function.LongFunction;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
