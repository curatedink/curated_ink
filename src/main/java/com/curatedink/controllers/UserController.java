package com.curatedink.controllers;

import com.curatedink.models.User;
import com.curatedink.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    // ------------------------------------------------------ Dependency Injection:
    private final UserRepo userDao;
//    private PasswordEncoder passwordEncoder; // Security


    public UserController(UserRepo userDao) {
        this.userDao = userDao;
    }

    // ------------------------------------------------------ User Sign-Up (Create):

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "tattoos/sign-up";
   }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
//        String hash = passwordEncoder.encode(user.getPassword()); // Security
//        user.setPassword(hash); // Security
        userDao.save(user);
        return "redirect:/login";
    }




}
