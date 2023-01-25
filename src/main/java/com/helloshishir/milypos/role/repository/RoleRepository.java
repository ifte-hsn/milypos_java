package com.helloshishir.milypos.role.repository;

import com.helloshishir.milypos.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
