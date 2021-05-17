package com.curatedink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ImageController {

    @GetMapping("/gallery")
    public String index(Model vModel) {
        vModel.addAttribute("gallery", imagesDao.findall());
        return "gallery/index";
    }
}
