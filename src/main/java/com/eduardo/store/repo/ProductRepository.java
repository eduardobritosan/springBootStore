package com.eduardo.store.repo;

import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    List<Product> findByProductCode(Long itemCode);
    List<Product> findByState(ProductStateEnum state);
    void deleteByProductCode(Long itemCode);
}
