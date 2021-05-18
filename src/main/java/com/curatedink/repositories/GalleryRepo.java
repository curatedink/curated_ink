package com.curatedink.repositories;

import com.curatedink.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepo extends JpaRepository<Image, Long> {

}
