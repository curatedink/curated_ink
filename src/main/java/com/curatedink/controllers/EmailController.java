package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {
    @GetMapping("/send-email")
    public String welcome() {

        return "";
    }

@PostMapping("/send-email")
public String insert() {

    return "";
    }
}
