package com.curatedink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ImageController {

    @GetMapping("/gallery")
    @ResponseBody
    public String index() {
        return "A gallery of tattoos";
    }
}
