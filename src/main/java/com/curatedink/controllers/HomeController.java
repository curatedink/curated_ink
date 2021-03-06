package com.curatedink.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "tattoos/landing";
    }

    @GetMapping("/about-us")
    public String about() {
        return "tattoos/about-us";
    }

}
