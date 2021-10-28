package com.eduardo.store.service;

import com.eduardo.store.dto.ProductSupplierDTO;

public interface IProductSupplierService {
    ProductSupplierDTO save(ProductSupplierDTO productSupplier);
    void delete(ProductSupplierDTO productSupplierDTO);
}
