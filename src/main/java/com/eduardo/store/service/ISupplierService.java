package com.eduardo.store.service;

import com.eduardo.store.dto.SupplierDTO;

import java.util.List;

public interface ISupplierService {
    List<SupplierDTO> findByName(String supplierName);
    List<SupplierDTO> findByCountry(String supplierCountry);
    SupplierDTO save(SupplierDTO supplier);

}
