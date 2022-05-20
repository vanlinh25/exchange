package com.example.demo.specification;


import com.example.demo.entity.Product;
import com.example.demo.entity.Product_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

  public static Specification<Product> baseCurrencyEqual(String name) {
    return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
        -> criteriaBuilder.equal(root.get(Product_.BASE_CURRENCY), name);
  }

  public static Specification<Product> quoteCurrencyEqual(String name) {
    return (Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder)
        -> criteriaBuilder.equal(root.get(Product_.QUOTE_CURRENCY), name);
  }

}


