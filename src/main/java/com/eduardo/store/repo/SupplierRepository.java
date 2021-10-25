package com.eduardo.store.repo;

import com.eduardo.store.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    List<Supplier> findByName(String name);
    List<Supplier> findByCountry(String country);
}
