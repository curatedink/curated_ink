package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.repositories.ImagesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ImageController {

    private final ImagesDao imagesDao;

    public ImageController(ImagesDao imagesDao) {
        this.imagesDao = imagesDao;
    }

    @GetMapping("/gallery")
    @ResponseBody
    public List<Image> getAllImages() {
            return imagesDao.findAll();
        }

    @GetMapping("/gallery/{id}")
    @ResponseBody
    public String getOneImage(@PathVariable long id, Model vModel) {
        vModel.addAttribute("image", imagesDao.getOne(id));
        return "gallery/{id}";
    }

}