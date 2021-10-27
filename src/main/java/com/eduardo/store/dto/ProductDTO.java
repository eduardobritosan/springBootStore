package com.eduardo.store.dto;

import com.eduardo.store.enums.ProductStateEnum;
import com.eduardo.store.model.ProductSupplier;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long idProduct;
    private Long itemCode;
    private String productDescription;
    private Double price;
    private ProductStateEnum state;
    private Date creationDate;
    private String creator;
    private List<ProductSupplier> suppliers;
}
