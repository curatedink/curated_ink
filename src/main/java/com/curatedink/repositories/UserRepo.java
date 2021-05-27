package com.curatedink.repositories;

import com.curatedink.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
//    List<User> findUsersByFollowingList(List<User> followingList);
}
