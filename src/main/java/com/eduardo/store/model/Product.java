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
    private Long itemCode;

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


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,
            mappedBy = "product")
    private List<ProductSupplier> suppliers;

}
