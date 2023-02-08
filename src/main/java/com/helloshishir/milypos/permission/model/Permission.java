package com.helloshishir.milypos.permission.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.helloshishir.milypos.role.model.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "permissions")
@Setter
@Getter
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roleList;

    @JsonIgnore
    public List<Role> getRoleList() {
        return roleList;
    }

    @JsonSetter
    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
