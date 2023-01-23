package com.helloshishir.milypos.category.service;

import com.helloshishir.milypos.category.model.Category;
import com.helloshishir.milypos.category.repository.CategoryRepository;
import com.helloshishir.milypos.util.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CriteriaRepository criteriaRepository;
    public Page<Category> getCategory(int pageNumber, int pageSize, String sortDirection, String sortBy, String searchQuery) {
        List<Category> categoryList = criteriaRepository.findAllWithFilter(pageNumber, pageSize, sortDirection, sortBy, searchQuery, Category.class);
        Long resultCount = criteriaRepository.getResultCount(searchQuery, Category.class);

        Pageable pageable = criteriaRepository.getPageable(pageNumber, pageSize, sortDirection, sortBy);

        return new PageImpl<>(categoryList, pageable, resultCount);
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Integer id) {
        Category category = findById(id);
        categoryRepository.delete(category);
    }
}
