package com.eduardo.store.repo;

import com.eduardo.store.model.ProductSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSupplierRepository extends
        JpaRepository<ProductSupplier, Long> {
}
