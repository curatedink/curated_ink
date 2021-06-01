package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.Style;
import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.UserRepo;
import com.curatedink.services.EmailService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class UserController {

    // Use the following line when we need access to the logged in user:
    //(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()

    // ------------------------------------------------------ Dependency Injection:

    private final UserRepo userDao;
    private final ImageRepo imagesDao;
    private PasswordEncoder passwordEncoder; // Security
    private final EmailService emailService;

    public UserController(UserRepo userDao, ImageRepo imagesDao, PasswordEncoder passwordEncoder, EmailService emailService) {
        this.userDao = userDao;
        this.imagesDao = imagesDao;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    // ------------------------------------------------------ User Sign-Up (Create):

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "tattoos/sign-up";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "style") List<Style> styles) {
        String hash = passwordEncoder.encode(user.getPassword()); // Security
        user.setPassword(hash); // Security
        user.setStyles(styles);
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
    public String update(@ModelAttribute User userToEdit, @RequestParam(name = "style") List<Style> styles) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userToEdit.setId(currentUser.getId());
        userToEdit.setPassword(currentUser.getPassword());
        userToEdit.setUsername(currentUser.getUsername());
        userToEdit.setStyles(styles);
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
    public String updateCanvas(@ModelAttribute User userToEdit, @RequestParam(name = "style") List<Style> styles) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userToEdit.setId(currentUser.getId());
        userToEdit.setPassword(currentUser.getPassword());
        userToEdit.setUsername(currentUser.getUsername());
        userToEdit.setStyles(styles);
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


    @PostMapping("/send-email")
    public String welcome() {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = userDao.getOne(owner.getId());
//    emailService.prepareAndSend(subject, body);
        return "artist-profile";
    }



    // ------------------------------------------------------ Delete a User:
    // Keep an eye on issues with foreign keys
    @PostMapping("/users/delete")
    public String deleteUser() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userDao.delete(userDao.getOne(currentUser.getId()));
        return "redirect:/";
    }

    // ------------------------------------------------------ Follow a User:
        // NEED TO CLEAN THIS UP
    // Visit another users page:
    @GetMapping("/profile/{id}")
    public String viewAnotherUserProfile(Model model, @PathVariable long id) {
        // Get current user and pass it forward:
        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getOne(principle.getId());
        model.addAttribute(currentUser);

        // Get selected image profile owner and pass it forward
        User profileOwner = userDao.getOne(id);
        model.addAttribute("user", profileOwner);
        List<User> followingList = profileOwner.getFollowingList();
        List<User> followerList = profileOwner.getFollowerList();

        //Checking to see if the current user is on the viewed profile users following list
        boolean showButton = !followerList.contains(currentUser);
        System.out.println(showButton); // TEST GOOD
        model.addAttribute("showButton", showButton);

        model.addAttribute("followingList", followingList);
        model.addAttribute("followerList", followerList);

        // Passing the profile owners images to their page
        List<Image> images = profileOwner.getImages();
        model.addAttribute("images", images);

        boolean userType = profileOwner.getIsArtist();
        if (userType) {
            return "users/artist";
        } else {
            return "users/canvas";
        }
    }

    @PostMapping("/users/follow/{id}") // put this action on the follow button
    public String followUser(@PathVariable long id){
        //get current user:
        User principle = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getOne(principle.getId());
        User userToFollow = userDao.getOne(id);
        List<User> following = currentUser.getFollowingList();
        following.add(userToFollow);
        currentUser.setFollowingList(following);
        userDao.save(currentUser);
        return "redirect:/profile/" + id;
    }

}
