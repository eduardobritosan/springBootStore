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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PRODUCT_SUPPLIER",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "supplier_id"))
    private List<Product> products;
}
