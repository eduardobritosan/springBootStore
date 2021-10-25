package com.eduardo.store.service;

import com.eduardo.store.model.Supplier;

import java.util.List;

public interface ISupplierService {
    List<Supplier> findByName(String supplierName);
    List<Supplier> findByCountry(String supplierCountry);
}
