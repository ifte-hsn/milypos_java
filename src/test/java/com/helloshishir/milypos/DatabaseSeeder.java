package com.helloshishir.milypos;

import com.github.javafaker.Faker;
import com.helloshishir.milypos.category.model.Category;
import com.helloshishir.milypos.category.repository.CategoryRepository;
import com.helloshishir.milypos.user.model.User;
import com.helloshishir.milypos.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DatabaseSeeder {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Test
    void seedDatabase() {
        seedCategoriesTable();
        seedUsersTable();
    }

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

    @Test
    void seedUsersTable() {
        Faker faker = new Faker();

        User user = new User();
        user.setEmail("ifte.hsn@gmail.com");
        user.setPassword(new BCryptPasswordEncoder().encode("123456"));
        user.setFirstName("Iftekhar");
        user.setLastName("Hossain");
        user.setWebsite("http://helloshishir.com");
        user.setEmployeeNum("0000001");
        user.setPhone("01717035892");
        user.setFax(faker.phoneNumber().subscriberNumber());
        user.setAddress(faker.address().fullAddress());
        user.setCity(faker.address().cityName());
        user.setState(faker.address().state());
        user.setZip(faker.address().zipCode());
        userRepository.save(user);

        for(int i = 0; i < 200; i++) {
            user = new User();
            user.setEmail(faker.internet().safeEmailAddress());
            user.setPassword(new BCryptPasswordEncoder().encode("123456"));
            user.setFirstName(faker.name().firstName());
            user.setLastName(faker.name().lastName());
            user.setWebsite(faker.internet().url());
            user.setEmployeeNum(faker.idNumber().ssnValid());
            user.setPhone(faker.phoneNumber().cellPhone());
            user.setFax(faker.phoneNumber().subscriberNumber());
            user.setAddress(faker.address().fullAddress());
            user.setCity(faker.address().cityName());
            user.setState(faker.address().state());
            user.setZip(faker.address().zipCode());
            userRepository.save(user);
        }


    }
}
