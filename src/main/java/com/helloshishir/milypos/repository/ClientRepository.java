package com.helloshishir.milypos.repository;

import com.helloshishir.milypos.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
