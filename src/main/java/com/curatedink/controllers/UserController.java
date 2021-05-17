package com.curatedink.controllers;

import com.curatedink.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
//    private UserRepository users;
//    private PasswordEncoder passwordEncoder;


    @GetMapping("/sign-up")
    @ResponseBody
    public String signUp(){
        return "get req for sign up page";
    }
//    public String showSignupForm(Model model){
//        model.addAttribute("user", new User());
//        return "tattoos/sign-up";
//    }


//    @PostMapping("/sign-up")
//    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword());
//        user.setPassword(hash);
//        users.save(user);
//        return "redirect:/login";
//    }

}
