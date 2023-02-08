package com.helloshishir.milypos.role.controller;


import com.helloshishir.milypos.role.model.Role;
import com.helloshishir.milypos.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    RoleService roleService;


    @GetMapping("index")
    public String index() {
        return "roles/index";
    }


    @GetMapping("getRoleList")
    @ResponseBody
    public ResponseEntity<Page<Role>> getRoleList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                  @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                                                  @RequestParam(value = "order", defaultValue = "ASC", required = false) String order,
                                                  @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
                                                  @RequestParam(value = "search", defaultValue = "", required = false) String searchQuery) {
        long pageNumber = (long) offset;
        long pageSize = (long) limit;

        if (pageNumber != 0 && pageSize != 0) {
            pageNumber = pageNumber / pageSize;
        }
        return new ResponseEntity<>(roleService.getRole(Math.toIntExact(pageNumber), Math.toIntExact(pageSize), order, sort, searchQuery), HttpStatus.OK);
    }

    @GetMapping("/create")
    public String showCreateForm(ModelMap modelMap) {
        Role role = new Role();
        modelMap.put("role", role);
        return "roles/form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, ModelMap modelMap) {
        Role role = roleService.findById(id);
        modelMap.put("role", role);
        return "roles/form";
    }
}
