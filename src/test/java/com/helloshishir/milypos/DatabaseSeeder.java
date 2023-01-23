package com.helloshishir.milypos;

import com.helloshishir.milypos.category.model.Category;
import com.helloshishir.milypos.category.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DatabaseSeeder {

    @Autowired
    CategoryRepository categoryRepository;

    @Test
    void seedCategoriesTable() {
        Category category = new Category();
        category.setName("Mobile");
        categoryRepository.save(category);


        category = new Category();
        category.setName("Tablet");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Laptop");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Gaming Console");
        categoryRepository.save(category);

        category = new Category();
        category.setName("DSLR");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Video Camera");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Television");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Printers");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Watch");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Motorcycle");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Shoes");
        categoryRepository.save(category);


        category = new Category();
        category.setName("Headphones");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Books");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Lighting");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Dog");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Cat");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Bird");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Pendrive");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Furniture");
        categoryRepository.save(category);

        category = new Category();
        category.setName("Bag");
        categoryRepository.save(category);
    }
}
