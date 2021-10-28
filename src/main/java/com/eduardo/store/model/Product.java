package com.eduardo.store.model;

import com.eduardo.store.enums.ProductStateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRODUCT")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;

    @Column(nullable = false, unique = true)
    private Long productCode;

    @Column(nullable = false)
    private String productDescription;

    @Column
    private Double price;

    @Column(columnDefinition = "varchar(255) default 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private ProductStateEnum state;

    @Column
    private Date creationDate;

    @Column
    private String creator;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "product")
    private List<ProductSupplier> suppliers;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "product")
    private List<PriceReduction> priceReductions;

}
