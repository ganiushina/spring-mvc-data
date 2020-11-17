package com.geekbrains.spring.mvc.repositories.specifications;

import com.geekbrains.spring.mvc.model.Student;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class StudentSpecifications {
    public static Specification<Student> scoreGEThan(int minScore) {
        return (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("score"), minScore);
    }

    public static Specification<Student> scoreLEThan(int maxScore) {
        return (Specification<Student>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("score"), maxScore);
    }
}
