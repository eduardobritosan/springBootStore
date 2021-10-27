package com.eduardo.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT_SUPPLIER")
public class ProductSupplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProductSupplier;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnoreProperties("suppliers")
    @JsonIgnore
    private Product product;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnoreProperties("products")
    private Supplier supplier;
}
