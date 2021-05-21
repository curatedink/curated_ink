package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.StyleRepo;
import com.curatedink.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ImageController {

    private final ImageRepo imagesDao;
    private final UserRepo usersDao;
    private final StyleRepo stylesDao;

    public ImageController(ImageRepo imagesDao, UserRepo usersDao, StyleRepo stylesDao) {
        this.imagesDao = imagesDao;
        this.usersDao = usersDao;
        this.stylesDao = stylesDao;
    }

    // view all images
    @GetMapping("/gallery")
    public String getAllImages(Model vModel) {
        vModel.addAttribute("images", imagesDao.findAll());
        return "tattoos/gallery";
    }

    // view image by id
    @GetMapping("/gallery/{id}")
    public String getOneImage(@PathVariable long id, Model vModel) {
        vModel.addAttribute("image", imagesDao.getOne(id));
        return "tattoos/gallery";
    }

    // create image
    @GetMapping("tattoos/artist-upload")
    public String create(Model vModel) {
        vModel.addAttribute("image", new Image());
        return "tattoos/artist-upload";
    }

    // insert image into images table
    @PostMapping("/artist-upload")
    public String insert(@ModelAttribute Image image) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        imagesDao.save(image);
        return "redirect:/profile-page";
    }

}