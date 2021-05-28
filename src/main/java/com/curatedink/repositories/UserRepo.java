package com.curatedink.repositories;

import com.curatedink.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
    User findByUsername(String username);
    Long findById(User id);

//    List<User> findAllByFollowingList(List<User> followingList);

//    User getOne(User userToGetFollowers);
}
