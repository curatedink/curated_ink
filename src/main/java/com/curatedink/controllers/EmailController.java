package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.User;
import com.curatedink.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmailController {


//private final EmailService emailService;

@GetMapping("/send-email")
    public String welcome() {

        return "tattoos/send-email";
    }

//@PostMapping("/send-email")
//    public String insert() {
//
//        return "artist-profile";
//    }
}
