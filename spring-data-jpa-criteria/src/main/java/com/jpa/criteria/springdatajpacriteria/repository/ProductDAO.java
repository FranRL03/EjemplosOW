package com.jpa.criteria.springdatajpacriteria.repository;

import com.jpa.criteria.springdatajpacriteria.models.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Product> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        CriteriaQuery<Product> findAll =  criteria.select(product);

        return entityManager.createQuery(findAll).getResultList();
    }

    public List<Product> findById(Long id){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        CriteriaQuery<Product> findById =  criteria
                .select(product)
                .where(criteriaBuilder.equal(product.get("id"), id));

        return entityManager.createQuery(findById).getResultList();
    }

    public List<Product> findByNameLike(String name){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        CriteriaQuery<Product> findByNameLike =  criteria
                .select(product)
                .where(criteriaBuilder.like(product.get("name"), name));

        return entityManager.createQuery(findByNameLike).getResultList();
    }

    public List<Product> findByManufacutreAndColor(String manufacture, List<String> colors){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Product> criteria = criteriaBuilder.createQuery(Product.class);
        Root<Product> product = criteria.from(Product.class);

        Predicate manufacturePred = criteriaBuilder.equal(product.get("manufacture"), manufacture);

        Predicate colorsPred = criteriaBuilder.or(colors.stream()
                .map(color -> criteriaBuilder.equal(product.get("color"), color))
                .toArray(Predicate[]::new));

        Predicate finalPred = criteriaBuilder.and(manufacturePred, colorsPred);

        CriteriaQuery<Product> findByPredicates =  criteria
                .select(product)
                .where(finalPred);
        
        return entityManager.createQuery(findByPredicates).getResultList();
    }
}
