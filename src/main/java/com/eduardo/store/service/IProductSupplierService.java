package com.eduardo.store.service;

import com.eduardo.store.dto.ProductSupplierDTO;
import com.eduardo.store.model.ProductSupplier;

public interface IProductSupplierService {
    ProductSupplierDTO save(ProductSupplierDTO productSupplier);
    void delete(ProductSupplierDTO productSupplierDTO);
    ProductSupplierDTO convertToDto(ProductSupplier productSupplier);
}
