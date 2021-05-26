package com.curatedink.repositories;

import com.curatedink.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepo extends JpaRepository<Image, Long> {
    List<Image> findAllByIsProfileImageIsFalse();
}
