package com.geekbrains.spring.mvc.repositories.specifications;

import com.geekbrains.spring.mvc.model.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ProductSpecifications {


    public static Specification<Product> coastGEThan(int minCoast) {
        return new Specification<Product>() {
            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("cost"), minCoast);
            }
        };
    }

    public static Specification<Product> coastLEThan(int maxCoast) {
        return (Specification<Product>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cost"), maxCoast);
    }

    // не знаю, как сделать правильно

//    public static Specification<Product> coastOrderByAsc(String sortType) {
//        return new Specification<Product>() {
//            @Override
//            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("coast")));
//                return criteriaBuilder.equal(root.get("coast"), sortType);
//            }
//        };
//    }
//
//    public static Specification<Product> coastOrderByDesc(String sortType) {
//        return new Specification<Product>() {
//            @Override
//            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                criteriaQuery.orderBy(criteriaBuilder.desc(root.get("coast")));
//                return criteriaBuilder. equal(root.get("coast"), sortType);
//            }
//        };
//    }


}


