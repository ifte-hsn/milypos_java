package com.helloshishir.milypos.user.service;

import com.helloshishir.milypos.user.model.User;
import com.helloshishir.milypos.user.repository.UserRepository;
import com.helloshishir.milypos.util.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    public Page<User> getUser(int pageNumber, int pageSize, String sortDirection, String sortBy, String searchQuery) {
        List<User> userList = criteriaRepository.findAllWithFilter(pageNumber, pageSize, sortDirection, sortBy, searchQuery, User.class);
        Long resultCount = criteriaRepository.getResultCount(searchQuery, User.class);
        Pageable pageable = criteriaRepository.getPageable(pageNumber, pageSize, sortDirection, sortBy);
        return new PageImpl<>(userList, pageable, resultCount);
    }

    public User findById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public User save(User user) {
        if(user.getPassword().trim().equals("")) {
            User userFromDb = findById(user.getId());
            user.setPassword(userFromDb.getPassword());
        }else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
        }
        return userRepository.save(user);
    }

    public void delete(Integer id) {
        User user = findById(id);
        userRepository.delete(user);
    }
}
