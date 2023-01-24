package com.helloshishir.milypos.user.repository;

import com.helloshishir.milypos.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
