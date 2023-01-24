package com.helloshishir.milypos.user.controller;


import com.helloshishir.milypos.user.model.User;
import com.helloshishir.milypos.user.service.UserService;
import com.helloshishir.milypos.util.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    StorageService storageService;

    @GetMapping("index")
    public String index() {
        return "users/index";
    }

    @GetMapping("getUserList")
    @ResponseBody
    public ResponseEntity<Page<User>> getUserList(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                  @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                                                  @RequestParam(value = "order", defaultValue = "ASC", required = false) String order,
                                                  @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
                                                  @RequestParam(value = "search", defaultValue = "", required = false) String searchQuery) {
        long pageNumber = (long) offset;
        long pageSize = (long) limit;

        if (pageNumber != 0 && pageSize != 0) {
            pageNumber = pageNumber / pageSize;
        }
        return new ResponseEntity<>(userService.getUser(Math.toIntExact(pageNumber), Math.toIntExact(pageSize), order, sort, searchQuery), HttpStatus.OK);
    }


    @GetMapping("/create")
    public String showCreateForm(ModelMap modelMap) {
        User user = new User();
        modelMap.put("user", user);
        return "users/form";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, ModelMap modelMap) {
        User user = userService.findById(id);
        modelMap.put("user", user);
        return "users/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute User user,
                       BindingResult bindingResult,
                       ModelMap modelMap,
                       RedirectAttributes redirectAttributes,
                       @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (bindingResult.hasErrors()) {
            return "users/form";
        }

        String fileName = "";

        if (!multipartFile.isEmpty()) {
            // get the file name
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhoto(fileName);
        }
        User saveUser = userService.save(user);

        if (!multipartFile.isEmpty()) {
            // get upload directory
            // in our case we will save it to the static resource directory
            // that exists in our class path
            fileName = "user-" + saveUser.getId() + "-" + fileName;
            storageService.save(multipartFile, fileName);

            saveUser.setPhoto(fileName);
            userService.save(saveUser);
        }


        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "User saved successfully!");
        return "redirect:/users/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, ModelMap modelMap,
                         RedirectAttributes redirectAttributes) {
        userService.delete(id);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "User deleted successfully!");

        return "redirect:/users/index";

    }

}
