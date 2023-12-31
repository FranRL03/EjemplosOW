package com.jpa.criteria.springdatajpacriteria;

import com.jpa.criteria.springdatajpacriteria.models.Category;
import com.jpa.criteria.springdatajpacriteria.models.Product;
import com.jpa.criteria.springdatajpacriteria.repository.CategoryRepository;
import com.jpa.criteria.springdatajpacriteria.repository.ProductRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class App {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(App.class, args);

		var categoryRepo = context.getBean(CategoryRepository.class);
		var productRepo = context.getBean(ProductRepository.class);

		Category category1 = new Category("category1");
		Category category2 = new Category("category2");
		Category category3 = new Category("category3");
		Category category4 = new Category("category4");

		categoryRepo.saveAll(List.of(category1, category2,category3,category4));
		productRepo.saveAll(List.of(
				new Product(null, "silla eléctrica", 33.33, LocalDate.of(2000,1,1), true, "Ford", "red", Set.of(category1, category2)),
				new Product(null, "silla colgante", 43.33, LocalDate.of(2001,1,1), true, "Ford", "green", Set.of(category1, category3)),
				new Product(null, "silla oficina", 56.33, LocalDate.of(2002,1,1), true, "Ford", "blue", Set.of(category1, category4)),
				new Product(null, "silla sofá", 63.33, LocalDate.of(2003,1,1), true, "Honda", "yellow", Set.of(category2, category3)),
				new Product(null, "laptop gaming", 73.33, LocalDate.of(2004,1,1), true, "Tesla", "red", Set.of(category1, category4)),
				new Product(null, "mesa plegable", 83.33, LocalDate.of(2005,1,1), true, "Ford", "red", Set.of(category3))

		));
	}

}
