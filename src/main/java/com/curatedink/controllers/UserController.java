package com.curatedink.controllers;

import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    // Use the following line when we need access to the logged in user:
    //(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()

    // ------------------------------------------------------ Dependency Injection:
    private final UserRepo userDao;
    private final ImageRepo imagesDao;
    private PasswordEncoder passwordEncoder; // Security


    public UserController(UserRepo userDao, ImageRepo imagesDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.imagesDao = imagesDao;
        this.passwordEncoder = passwordEncoder;
    }

    // ------------------------------------------------------ User Sign-Up (Create):

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "tattoos/sign-up";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword()); // Security
        user.setPassword(hash); // Security
        userDao.save(user);
        return "redirect:/login";
    }


    // ------------------------------------------------------ Artist Edit Profile (Update):

    @GetMapping("/artist-edit")
    public String editArtistProfile(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getOne(currentUser.getId());
        model.addAttribute("user", userToEdit);
        return "users/artist-edit";
    }

    @PostMapping("/users/artist-edit")
    public String update(@ModelAttribute User userToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userToEdit.setId(currentUser.getId());
        userToEdit.setPassword(currentUser.getPassword());
        userToEdit.setUsername(currentUser.getUsername());
        userDao.save(userToEdit);
        return "redirect:/profile-page";
    }


    // ------------------------------------------------------ Canvas Edit Profile (Update):

    @GetMapping("/canvas-edit")
    public String editCanvasProfile(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getOne(currentUser.getId());
        model.addAttribute("user", userToEdit);
        return "users/canvas-edit";
    }

    @PostMapping("/users/canvas-edit")
    public String updateCanvas(@ModelAttribute User userToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userToEdit.setId(currentUser.getId());
        userToEdit.setPassword(currentUser.getPassword());
        userToEdit.setUsername(currentUser.getUsername());
        userDao.save(userToEdit);
        return "redirect:/profile-page";
    }

    // -----------------------------------------------------

    // After logging in the user is directed here to then be directed according to their
    // usertype (isArtist) value
    @GetMapping("/profile-page")
    public String pointToProfile(Model model) {
        // Grabbing the current user object with the next line
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = String.valueOf(currentUser.getId());
        model.addAttribute("user", currentUser);
        model.addAttribute("images", userDao.getOne(Long.valueOf(currentUserId)).getImages());
        boolean userType = currentUser.getIsArtist();
        if (userType) {
            return "users/artist-profile";
        } else {
            return "users/canvas-profile";
        }
    }

    // ------------------------------------------------------ Delete a User:
    // Keep an eye on issues with foreign keys
    @PostMapping("/users/delete")
    public String deleteUser(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.delete(userDao.getOne(currentUser.getId()));
        return "redirect:/";
    }

}
