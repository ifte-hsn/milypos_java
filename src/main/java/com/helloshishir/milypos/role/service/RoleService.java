package com.helloshishir.milypos.role.service;

import com.helloshishir.milypos.role.model.Role;
import com.helloshishir.milypos.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }
}
