package com.helloshishir.milypos.sales;

import com.helloshishir.milypos.client.model.Client;
import com.helloshishir.milypos.user.model.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "sales")
@Data
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @OneToOne
    User user;

    @OneToOne
    Client client;

    @Column(name = "invoice_no")
    String invoiceNo;

    @Column(name = "tax")
    double tax;

    @Column(name = "subtotal")
    double subtotal;

    @Column(name = "total")
    double total;

    @Column(name = "payment_method")
    String paymentMethod;

    @CreationTimestamp
    @Column(name = "created_at")
    Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Date updatedAt;
}
