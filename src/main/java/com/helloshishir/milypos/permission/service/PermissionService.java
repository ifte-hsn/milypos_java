package com.helloshishir.milypos.permission.service;

import com.helloshishir.milypos.permission.model.Permission;
import com.helloshishir.milypos.permission.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    @Autowired
    PermissionRepository permissionRepository;

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }


    public Permission findById(Integer id) {
        return permissionRepository.findById(id).orElse(null);
    }
}
