package com.curatedink.repositories;

import com.curatedink.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
