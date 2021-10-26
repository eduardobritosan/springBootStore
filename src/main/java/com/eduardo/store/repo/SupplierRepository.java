package com.eduardo.store.repo;

import com.eduardo.store.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    List<Supplier> findByName(String name);
    List<Supplier> findByCountry(String country);
}
