package com.helloshishir.milypos.category.repository;

import com.helloshishir.milypos.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
