package com.curatedink.repositories;

import com.curatedink.models.Image;
import com.curatedink.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
    List<Image> findAllByIsProfileImageIsFalse();
    List<Image> findImagesByUser();
//    List<Image> findImageByUserFollowingList(List <User> random);
}
