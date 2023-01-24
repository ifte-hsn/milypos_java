package com.helloshishir.milypos.user.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "photo")
    String photo;

    @Column(name = "website")
    String website;

    @Column(name = "employee_num")
    String employeeNum;

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

    @Column(name = "is_active")
    boolean isActive;
}
