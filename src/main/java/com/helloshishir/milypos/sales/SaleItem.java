package com.helloshishir.milypos.sales;


import com.helloshishir.milypos.product.model.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sales")
@Data
public class SaleItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @OneToOne
    Product product;
    @Column(name = "quantity")
    Integer quantity;

    @Column(name = "total")
    double total;

    @OneToOne
    Sale sale;
}
