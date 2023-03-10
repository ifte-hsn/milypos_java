package com.helloshishir.milypos.country.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "countries")
@Data
public class Country {
    @Id
    Integer id;

    String name;
}
