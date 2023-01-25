package com.helloshishir.milypos.role.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.helloshishir.milypos.permission.model.Permission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "roles")
@Setter
@Getter
@ToString
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String name;

    @ManyToMany
    @JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"))
    List<Permission> permissionList;

    @JsonIgnore
    public List<Permission> getPermissionList() {
        return permissionList;
    }

    @JsonSetter
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
