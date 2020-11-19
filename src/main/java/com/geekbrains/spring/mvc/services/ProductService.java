package com.geekbrains.spring.mvc.services;

import com.geekbrains.spring.mvc.exceptions.ProductNotFoundException;
import com.geekbrains.spring.mvc.model.Product;
import com.geekbrains.spring.mvc.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private ProductsRepository productRepository;

    @Autowired
    public void setProductRepository(ProductsRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Can't found product with id = " + id));
    }

    public Product findByTitle(String title) {
        return productRepository.findOneByTitle(title);
    }

    public List<Product> findByOrderByCoast(String sortType) {
        if (sortType.equals("asc"))
          return productRepository.findByOrderByCoastAsc();
        if (sortType.equals("desc"))
            return productRepository.findByOrderByCoastDesc();
        else return productRepository.findByOrderByCoastAsc();
    }

    public List<Product> findByMinCoast(int minCoast) {
        return productRepository.findAllByCoastGreaterThan(minCoast);
    }

    public List<Product> findByCoast(int coast) {
        return productRepository.findByCoast(coast, Sort.by("coast").ascending());
    }

    public Page<Product> findByPage(int pageNumber, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Page<Product> findAll(Specification<Product> spec, Integer page) {
        if (page < 1L) {
            page = 1;
        }
        return productRepository.findAll(spec, PageRequest.of(page - 1, 10));
    }
}
