package com.helloshishir.milypos.permission.repository;

import com.helloshishir.milypos.permission.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Integer> {
}
