package com.eduardo.store.service;

import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.dto.SupplierDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findByItemCode(Long itemCode);
    List<ProductDTO> findByState(String state);
    ProductDTO save(ProductDTO product);
    ProductDTO update(ProductDTO productDTO, Long itemCode);
    void deleteByItemCode(Long itemCode);
    ProductDTO deactivate(Long itemCode);
    ProductDTO addSupplier(SupplierDTO supplierDTO, Long itemCode);
}
