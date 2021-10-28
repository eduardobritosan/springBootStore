package com.eduardo.store.model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Data
@Entity
@Table(name = "SUPPLIER")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSupplier;

    @Column(nullable = false, unique = true)
    private Long supplierCode;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "supplier")
    private List<ProductSupplier> products;
}
