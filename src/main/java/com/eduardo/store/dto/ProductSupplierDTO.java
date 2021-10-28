package com.eduardo.store.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSupplierDTO {
    private Long idProductSupplier;
    private Long productSupplierCode;
    private ProductDTO productDTO;
    private SupplierDTO supplierDTO;
}
