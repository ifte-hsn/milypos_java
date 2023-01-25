package com.helloshishir.milypos.client.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "clients")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "email")
    String email;


    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "photo")
    String photo;


    @Column(name = "sex")
    String sex;

    @Column(name = "phone")
    String phone;

    @Column(name = "fax")
    String fax;

    @Column(name = "address", columnDefinition = "TEXT")
    String address;

    @Column(name = "city")
    String city;

    @Column(name = "state")
    String state;

    @Column(name = "zip")
    String zip;
}
