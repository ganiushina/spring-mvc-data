package com.geekbrains.spring.mvc.repositories;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    Product findOneByTitle(String name);
    List<Product> findAllByCoastGreaterThan(int minCoast);
    List<Product> findByOrderByCoastAsc();
    List<Product> findByOrderByCoastDesc();

    List<Product> findByCoast(int coast, Sort sort);

}