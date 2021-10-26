package com.eduardo.store.model;

import com.eduardo.store.enums.ProductStateEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false, unique = true)
    private String itemCode;

    @Column
    private String productDescription;

    @Column
    private Double price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductStateEnum state;

    @Column(nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private String creator;

    @ManyToMany(mappedBy = "products")
    private List<Supplier> suppliers;

}
