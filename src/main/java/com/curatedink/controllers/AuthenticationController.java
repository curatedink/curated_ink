package com.curatedink.controllers;

import com.curatedink.models.User;
import com.curatedink.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    private final UserRepo userDao;

    public AuthenticationController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "tattoos/login";
    }

    // ----------------- For Testing Only. Remove below when setting up spring security

//    // Test Artist Profile:
//    @PostMapping("/login")
//    public String login(Model model, @RequestParam String email, @RequestParam String password) {
//        User user = userDao.findByEmail(email);
//        System.out.println(user.getDisplayName());
//        model.addAttribute("user", user);
//        boolean userType = user.getIsArtist();
//        if (userType) {
//            return "users/artist-profile";
//        } else {
//            return "users/canvas-profile";
//        }
//    }


    // ----------------------------------> Remove the above when implementing security
}


