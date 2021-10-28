package com.eduardo.store.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PRICE_REDUCTION")
public class PriceReduction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPriceReduction;

    @Column(nullable = false, unique = true)
    private Long priceReductionCode;

    @Column(nullable = false)
    private Double newPrice;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Product product;
}
