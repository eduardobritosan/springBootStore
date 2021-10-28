package com.eduardo.store.dto;

import com.eduardo.store.enums.ProductStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long productCode;
    private String productDescription;
    private Double price;
    private ProductStateEnum state;
    private Date creationDate;
    private String creator;
    private List<ProductSupplierDTO> suppliers;
    private List<PriceReductionDTO> priceReductions;
}
