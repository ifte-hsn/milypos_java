package com.helloshishir.milypos.product.model;

import com.helloshishir.milypos.category.model.Category;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "products")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "code", unique = true)
    String code;

    @Column(name = "image")
    String image;

    @Column(name = "stock")
    double stock;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "purchase_price")
    double purchasePrice;

    @Column(name = "selling_price")
    double sellingPrice;

    @Column(name = "sales")
    Integer sales;

    @OneToOne
    Category category;
}
