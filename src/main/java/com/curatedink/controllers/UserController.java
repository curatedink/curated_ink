package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.Style;
import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.StyleRepo;
import com.curatedink.repositories.UserRepo;
import com.curatedink.services.MailtrapService;
import com.curatedink.services.SendGridService;
import com.sendgrid.Content;
import com.sendgrid.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    // Use the following line when we need access to the logged in user:
    //(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()

    // ------------------------------------------------------ Dependency Injection:

    private final UserRepo userDao;
    private final ImageRepo imagesDao;
    private PasswordEncoder passwordEncoder; // Security
    private final MailtrapService mailtrapService;
    private final StyleRepo stylesDao;

    @Value("${filestackApiKey}")
    private String filestackApiKey;

    @Value("spring.sendgrid.api-key")
    private String sendgridApiKey;

    SendGridService sendGridService;

    public UserController(UserRepo userDao, ImageRepo imagesDao, PasswordEncoder passwordEncoder, StyleRepo stylesDao, MailtrapService mailtrapService, SendGridService sendGridService) {
        this.userDao = userDao;
        this.imagesDao = imagesDao;
        this.passwordEncoder = passwordEncoder;
        this.mailtrapService = mailtrapService;
        this.sendGridService = sendGridService;
        this.stylesDao = stylesDao;
    }

    // ------------------------------------------------------ User Sign-Up (Create):

    @GetMapping("/sign-up")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "tattoos/sign-up";
    }


    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, @RequestParam(name = "style", required = false) List<Style> styles ) {
        String hash = passwordEncoder.encode(user.getPassword()); // Security
        user.setPassword(hash); // Security
        styleValidation(user, styles);
        return "redirect:/login";
    }


    // ------------------------------------------------------ Artist Edit Profile (Update):

    @GetMapping("/artist-edit")
    public String editArtistProfile(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getOne(currentUser.getId());
        model.addAttribute("user", userToEdit);
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "users/artist-edit";
    }

    @PostMapping("/users/artist-edit")
    public String update(@ModelAttribute User userToEdit, @RequestParam(name = "style", required = false) List<Style> styles) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        userToEdit.setId(currentUser.getId());
        userToEdit.setPassword(currentUser.getPassword());
        userToEdit.setUsername(currentUser.getUsername());
        userToEdit.setIsArtist(currentUser.getIsArtist());
        styleValidation(userToEdit, styles);
        return "redirect:/profile-page";
    }

   // Style Validation method used to make sure that the styles list is never empty
    private void styleValidation(@ModelAttribute User userToEdit, @RequestParam(name = "style", required = false) List<Style> styles) {
        if (styles != null) {
            userToEdit.setStyles(styles);
        } else {
            List<Style> defaultStyles = new ArrayList<>();
            defaultStyles.add(stylesDao.getOne(13L));
            userToEdit.setStyles(defaultStyles);
        }

        userDao.save(userToEdit);
    }


    // ------------------------------------------------------ Canvas Edit Profile (Update):

    @GetMapping("/canvas-edit")
    public String editCanvasProfile(Model model) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User userToEdit = userDao.getOne(currentUser.getId());
        model.addAttribute("user", userToEdit);
        model.addAttribute("filestackApiKey", filestackApiKey);
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
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.getOne(principal.getId());
        String currentUserId = String.valueOf(currentUser.getId());
        Image image = new Image();
        model.addAttribute("image", image);
        model.addAttribute("filestackApiKey", filestackApiKey);
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
//    // Keep an eye on issues with foreign keys
//    @PostMapping("/users/delete")
//    public String deleteUser() {
//        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        userDao.delete(userDao.getOne(currentUser.getId()));
//        return "redirect:/";
//    }

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

        // Checking to see if the current user is on the viewed profile users following list
        // && is not viewing their own profile
        boolean showButton = (!followerList.contains(currentUser)) && (id != currentUser.getId()) ;
//        System.out.println(showButton); // TEST GOOD
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
    public String followUser(@PathVariable long id) {
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

    @GetMapping("/send-email/{id}")
    public String email(@PathVariable long id, Model model) {
        // Get selected image profile owner and pass it forward
        User profileOwner = userDao.getOne(id);
        model.addAttribute("profileOwner", profileOwner);
        return "tattoos/send-email";
    }

    @PostMapping("/send-email")
    public String sendEmail(
            @ModelAttribute User profileOwner,
            @RequestParam(name = "ownerUsername") String username,
            @RequestParam(name = "emailSubject") String emailSubject,
            @RequestParam(name = "emailBody") String emailBody
    ) {
        User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User emailFrom = userDao.getOne(owner.getId());
        String emailFrom = "curated.ink.com@gmail.com";
        String userToEmail = userDao.findByUsername(username).getEmail();
        sendGridService.sendEmail(emailFrom, userToEmail, emailSubject, emailBody);
        return "redirect:/profile-page";
    }

// Mailtrap API
//    @PostMapping("/send-email")
//    public String sendEmail(
//            @ModelAttribute User profileOwner,
//            @RequestParam(name = "ownerUsername") String username,
//            @RequestParam(name = "emailSubject") String emailSubject,
//            @RequestParam(name = "emailBody") String emailBody
//    ) {
//    User owner = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    User emailFrom = userDao.getOne(owner.getId());
//    User userToEmail = userDao.findByUsername(username);
//    mailtrapService.prepareAndSend(emailFrom, userToEmail.getEmail(), emailSubject, emailBody);
//        return "redirect:/profile-page";
//    }


}

