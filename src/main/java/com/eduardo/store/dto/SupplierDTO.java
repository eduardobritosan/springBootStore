package com.eduardo.store.dto;

import lombok.Data;

@Data
public class SupplierDTO {
    private Long idSupplier;
    private Long supplierCode;
    private String name;
    private String country;
}
