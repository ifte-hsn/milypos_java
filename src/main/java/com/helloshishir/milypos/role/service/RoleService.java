package com.helloshishir.milypos.role.service;


import com.helloshishir.milypos.role.model.Role;
import com.helloshishir.milypos.role.repository.RoleRepository;
import com.helloshishir.milypos.util.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }


    public Page<Role> getRole(int pageNumber, int pageSize, String sortDirection, String sortBy, String searchQuery) {
        List<Role> roleList = criteriaRepository.findAllWithFilter(pageNumber, pageSize, sortDirection, sortBy, searchQuery, Role.class);
        Long resultCount = criteriaRepository.getResultCount(searchQuery, Role.class);
        Pageable pageable = criteriaRepository.getPageable(pageNumber, pageSize, sortDirection, sortBy);
        return new PageImpl<>(roleList, pageable, resultCount);
    }
}
