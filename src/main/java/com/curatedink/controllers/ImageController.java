package com.curatedink.controllers;

import com.curatedink.repositories.ImagesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    private final ImagesDao imagesDao;

    public ImageController(ImagesDao imagesDao) {
        this.imagesDao = imagesDao;
    }

// view all images
    @GetMapping("/gallery")
    public String getAllImages(Model vModel) {
            vModel.addAttribute("images", imagesDao.findAll());
            return "tattoos/gallery";
        }

// view image by id
    @GetMapping("/gallery/{id}")
    @ResponseBody
    public String getOneImage(@PathVariable long id, Model vModel) {
        vModel.addAttribute("image", imagesDao.getOne(id));
        return "gallery/{id}";
    }

//

}