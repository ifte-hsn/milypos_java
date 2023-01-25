package com.helloshishir.milypos.country.repository;

import com.helloshishir.milypos.country.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}
