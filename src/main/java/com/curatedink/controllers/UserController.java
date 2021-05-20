package com.curatedink.controllers;

import com.curatedink.models.User;
import com.curatedink.repositories.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    // ------------------------------------------------------ Artist Edit Profile (Update):

    @GetMapping("/artist-edit/{id}")
    public String editArtistProfile(@PathVariable("id")Long id, Model model){
        User currentUser = userDao.getOne(1L); // Artist user
        model.addAttribute("user", currentUser);
        return "users/artist-edit";
    }

    @PostMapping("/artist-edit/{id}")
    public String update(@ModelAttribute User user, @PathVariable Long id){
        userDao.save(user);
        return "redirect:/users/artist-profile" + id;
    }

    // -----------------------------------------------------

}
