package com.helloshishir.milypos.category.controller;

import com.helloshishir.milypos.category.model.Category;
import com.helloshishir.milypos.category.service.CategoryService;
import com.helloshishir.milypos.util.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    StorageService storageService;

    @GetMapping("index")
    String index() {
        return "categories/index";
    }

    @GetMapping("list")
    @ResponseBody
    public ResponseEntity<Page<Category>> getCustomer(@RequestParam(value = "offset", defaultValue = "0", required = false) int offset,
                                                      @RequestParam(value = "limit", defaultValue = "10", required = false) int limit,
                                                      @RequestParam(value = "order", defaultValue = "ASC", required = false) String order,
                                                      @RequestParam(value = "sort", defaultValue = "id", required = false) String sort,
                                                      @RequestParam(value = "search", defaultValue = "", required = false) String searchQuery) {
        long pageNumber = (long) offset;
        long pageSize = (long) limit;

        if(pageNumber != 0 && pageSize !=0) {
            pageNumber = pageNumber/pageSize;
        }
        return new ResponseEntity<>(categoryService.getCategory(Math.toIntExact(pageNumber), Math.toIntExact(pageSize), order, sort, searchQuery), HttpStatus.OK);
    }



    @GetMapping("/create")
    public String showCreateForm(ModelMap modelMap) {
        Category category = new Category();
        modelMap.put("category", category);
        return "categories/form";

    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, ModelMap modelMap) {
        Category category = categoryService.findById(id);
        modelMap.put("category", category);
        return "categories/form";
    }

    @PostMapping("/save")
    public String save( @ModelAttribute Category category,
                        BindingResult bindingResult,
                        ModelMap modelMap,
                        RedirectAttributes redirectAttributes,
                        @RequestParam("image") MultipartFile multipartFile) throws IOException {
        if (bindingResult.hasErrors()) {
            return "categories/form";
        }

        String fileName = "";

        if(!multipartFile.isEmpty()) {
            // get the file name
            fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            category.setPhoto(fileName);
        }
        Category saveCategory = categoryService.save(category);

        if(!multipartFile.isEmpty()) {
            // get upload directory
            // in our case we will save it to the static resource directory
            // that exists in our class path
            String uploadDir = ResourceUtils.getURL("classpath:").getPath()+"/static/uploads/categories/"+saveCategory.getId();

            storageService.clearDir(uploadDir);
            storageService.save(multipartFile, uploadDir, fileName);
        }
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "User saved successfully!");
        return "redirect:/categories/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, ModelMap modelMap,
                         RedirectAttributes redirectAttributes) {
        categoryService.delete(id);
        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "User saved successfully!");

        return "redirect:/categories/index";

    }
}
