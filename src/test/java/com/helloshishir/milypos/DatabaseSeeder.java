package com.helloshishir.milypos;

import com.github.javafaker.Faker;
import com.helloshishir.milypos.category.model.Category;
import com.helloshishir.milypos.category.repository.CategoryRepository;
import com.helloshishir.milypos.client.model.Client;
import com.helloshishir.milypos.client.repository.ClientRepository;
import com.helloshishir.milypos.country.repository.CountryRepository;
import com.helloshishir.milypos.permission.model.Permission;
import com.helloshishir.milypos.permission.repository.PermissionRepository;
import com.helloshishir.milypos.role.model.Role;
import com.helloshishir.milypos.role.repository.RoleRepository;
import com.helloshishir.milypos.user.model.User;
import com.helloshishir.milypos.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class DatabaseSeeder {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Test
    void seedDatabase() {
        seedCategoriesTable();
        seedUsersTable();
        seedClientsTable();
        seedRoleTable();
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

    @Test
    void seedClientsTable() {
        Client client = null;
        Faker faker = new Faker();

        for(int i = 0; i < 100; i++) {
            client = new Client();
            client.setEmail(faker.internet().safeEmailAddress());
            client.setFirstName(faker.name().firstName());
            client.setLastName(faker.name().lastName());
            client.setSex(faker.demographic().sex());
            client.setPhone(faker.phoneNumber().cellPhone());
            client.setFax(faker.phoneNumber().phoneNumber());
            client.setAddress(faker.address().fullAddress());
            client.setCity(faker.address().city());
            client.setState(faker.address().state());
            client.setZip(faker.address().zipCode());
            clientRepository.save(client);
        }
    }

    @Test
    void seedRoleTable() {
        Role superAdmin = new Role();
        superAdmin.setName("Super Admin");
        superAdmin = roleRepository.save(superAdmin);

        Role admin = new Role();
        admin.setName("Admin");
        admin = roleRepository.save(admin);

        String[] userPermission = {"add_user", "view_user", "edit_user", "delete_user","export_users"};
        String[] clientsPermission = {"add_client", "view_client", "edit_client", "delete_client","export_client"};
        String[] productsPermission = {"add_product", "view_product", "edit_product", "delete_product","export_product"};
        String[] categoriesPermission = {"add_category", "view_category", "edit_category", "delete_category","export_category"};
        String[] rolePermission = {"add_role", "view_role", "edit_role", "delete_role","export_role"};

        for (String p: userPermission) {
            Permission permission = new Permission();
            permission.setName(p);
            permission.setRoleList(List.of(superAdmin));
            permissionRepository.save(permission);
        }


        for (String p: clientsPermission) {
            Permission permission = new Permission();
            permission.setName(p);
            permission.setRoleList(List.of(superAdmin));
            permissionRepository.save(permission);
        }

        for (String p: productsPermission) {
            Permission permission = new Permission();
            permission.setName(p);
            permission.setRoleList(List.of(superAdmin));
            permissionRepository.save(permission);
        }

        for (String p: categoriesPermission) {
            Permission permission = new Permission();
            permission.setName(p);
            permission.setRoleList(List.of(superAdmin));
            permissionRepository.save(permission);
        }

        for (String p: rolePermission) {
            Permission permission = new Permission();
            permission.setName(p);
            permission.setRoleList(List.of(superAdmin));
            permissionRepository.save(permission);
        }



    }
}
