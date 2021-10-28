package com.eduardo.store.service;

import com.eduardo.store.dto.SupplierDTO;
import com.eduardo.store.model.Supplier;

import java.util.List;

public interface ISupplierService {
    List<SupplierDTO> findByName(String supplierName);
    List<SupplierDTO> findByCountry(String supplierCountry);
    List<SupplierDTO> findSupplierCode(Long supplierCode);
    SupplierDTO save(SupplierDTO supplier);
    SupplierDTO convertToDto(Supplier supplier);
    Supplier convertToPojo(SupplierDTO supplierDTO);

}
