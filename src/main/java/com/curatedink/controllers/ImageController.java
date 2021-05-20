package com.curatedink.controllers;

import com.curatedink.repositories.ImagesRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    private final ImagesRepo imagesRepo;

    public ImageController(ImagesRepo imagesRepo) {
        this.imagesRepo = imagesRepo;
    }

// view all images
    @GetMapping("/gallery")
    public String getAllImages(Model vModel) {
            vModel.addAttribute("images", imagesRepo.findAll());
            return "tattoos/gallery";
        }

// view image by id
    @GetMapping("/gallery/{id}")
    @ResponseBody
    public String getOneImage(@PathVariable long id, Model vModel) {
        vModel.addAttribute("image", imagesRepo.getOne(id));
        return "gallery/{id}";
    }

//

}