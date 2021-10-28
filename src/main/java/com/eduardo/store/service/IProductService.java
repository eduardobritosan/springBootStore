package com.eduardo.store.service;

import com.eduardo.store.dto.PriceReductionDTO;
import com.eduardo.store.dto.ProductDTO;
import com.eduardo.store.dto.SupplierDTO;

import java.util.List;

public interface IProductService {
    List<ProductDTO> findAll();
    List<ProductDTO> findByProductCode(Long productCode);
    List<ProductDTO> findByState(String state);
    ProductDTO save(ProductDTO product);
    ProductDTO update(ProductDTO productDTO, Long productCode);
    void deleteByProductCode(Long productCode);
    ProductDTO deactivate(Long productCode);
    ProductDTO addSupplier(SupplierDTO supplierDTO, Long productCode);
    ProductDTO addPriceReduction(PriceReductionDTO priceReductionDTO, Long priceReductionCode);
}
