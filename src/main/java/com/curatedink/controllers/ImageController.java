package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.Style;
import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.StyleRepo;
import com.curatedink.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class ImageController {

    private final ImageRepo imagesDao;
    private final UserRepo usersDao;
    private final StyleRepo stylesDao;

    @Value("${filestackApiKey}")
    private String filestackApiKey;

    public ImageController(ImageRepo imagesDao, UserRepo usersDao, StyleRepo stylesDao) {
        this.imagesDao = imagesDao;
        this.usersDao = usersDao;
        this.stylesDao = stylesDao;
    }


//         view all images
//    @GetMapping("/gallery")
//    public String getAllImages(Model vModel) {
//        vModel.addAttribute("images", imagesDao.findAllByIsProfileImageIsFalse());
//        return "tattoos/gallery";
//    }


    // view image by id
//    @GetMapping("/gallery/{id}")
//    public String getOneImage(@PathVariable long id, Model vModel) {
//        vModel.addAttribute("image", imagesDao.getOne(id));
//        return "tattoos/gallery";
//    }


    // json - world gallery
    @GetMapping(value = "/gallery.json")
    public @ResponseBody List<Image> getAllImagesInJSONFormat() {
        List<Image> allNonProfileImages = imagesDao.findAllByIsProfileImageIsFalse();
        Collections.reverse(allNonProfileImages);
        return allNonProfileImages;
    }

    @GetMapping("/gallery")
    public String getAllImagesWithAjax() {
        return "tattoos/gallery";
    }

    //curated-gallery

    @GetMapping(value = "/curated-gallery.json")
    @ResponseBody
    public List<Object> getAllCurrentUserFollowingInJSONFormat() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = usersDao.getOne(principal.getId());

        List<User> currentUserFollowingList = currentUser.getFollowingList();
//        List<Image> allImages = imagesDao.findAllByIsProfileImageIsFalse();
//        Collections.reverse(allImages);
        List<Object> followedImages = new ArrayList <Object>();

        for (User user : currentUserFollowingList) {
            for (Image image : user.getImages()) {
                if (!image.getIsProfileImage()){
                    followedImages.add(image);
                }
            }
//            followedImages.addAll(user.getImages());
//            System.out.println(followedImages);
            Collections.reverse(followedImages);
        }
        return followedImages;
    }


    //   delete image
    @PostMapping("/tattoos/delete/{id}")
    public String deleteImage(@PathVariable("id") long id) {
        Image imageToDelete = imagesDao.getOne(id);
        List<Style> imageStyles = imageToDelete.getStyles();
        imageStyles.clear();
        imagesDao.delete(imageToDelete);
        return "redirect:/profile-page";
    }


    // create artist image
    @GetMapping("tattoos/artist-upload")
    public String create(Model model) {
        model.addAttribute("image", new Image());
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "tattoos/artist-upload";
    }

    // save artist image to images table
    @PostMapping("/artist-upload")
    public String insert(@ModelAttribute Image image, @RequestParam(name = "style", required = false) List<Style> styles) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        if (styles != null) {
            image.setStyles(styles);
        } else {
            List<Style> defaultStyles = new ArrayList<>();
            defaultStyles.add(stylesDao.getOne(13L));
            image.setStyles(defaultStyles);
        }
        imagesDao.save(image);
        return "redirect:/profile-page";
    }


    // create canvas image
    @GetMapping("tattoos/canvas-upload")
    public String createCanvasImage(Model model) {
        model.addAttribute("image", new Image());
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "tattoos/canvas-upload";
    }


    // save canvas image to images table
    @PostMapping("/canvas-upload")
    public String insertCanvasImage(@ModelAttribute Image image, @RequestParam(name = "style", required = false) List<Style> styles) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        if (styles != null) {
            image.setStyles(styles);
        } else {
            List<Style> defaultStyles = new ArrayList<>();
            defaultStyles.add(stylesDao.getOne(13L));
            image.setStyles(defaultStyles);
        }
        imagesDao.save(image);
        return "redirect:/profile-page";
    }


    // edit canvas image
    @GetMapping("/canvas-image-edit/{id}")
    public String editCanvasImage(@PathVariable("id") long id, Model model) {
        Image imageToEdit = imagesDao.getOne(id);
        model.addAttribute("image", imageToEdit);
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "tattoos/canvas-image-edit";
    }


    // save canvas image edit
    @PostMapping("/tattoos/canvas-image-edit/{id}")
    public String updateCanvasImage(@ModelAttribute Image imageToEdit, @RequestParam(name = "style", required = false) List<Style> styles) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        imageToEdit.setUser(currentUser);
        if (styles != null) {
            imageToEdit.setStyles(styles);
        } else {
            List<Style> defaultStyles = new ArrayList<>();
            defaultStyles.add(stylesDao.getOne(13L));
            imageToEdit.setStyles(defaultStyles);
        }
        imagesDao.save(imageToEdit);
        return "redirect:/profile-page";
    }


    // edit artist image
    @GetMapping("/artist-image-edit/{id}")
    public String editArtistImage(@PathVariable("id") long id, Model model) {
        Image imageToEdit = imagesDao.getOne(id);
        model.addAttribute("image", imageToEdit);
        model.addAttribute("filestackApiKey", filestackApiKey);
        return "tattoos/artist-image-edit";
    }

    // save artist image edit
    @PostMapping("/tattoos/artist-image-edit/{id}")
    public String updateArtistImage(@ModelAttribute Image imageToEdit, @RequestParam(name = "style", required = false) List<Style> styles) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        imageToEdit.setUser(currentUser);
        if (styles != null) {
            imageToEdit.setStyles(styles);
        } else {
            List<Style> defaultStyles = new ArrayList<>();
            defaultStyles.add(stylesDao.getOne(13L));
            imageToEdit.setStyles(defaultStyles);
        }
        imagesDao.save(imageToEdit);
        return "redirect:/profile-page";
    }

    // Profile image
    @PostMapping("/profile-image")
    public String addProfileImage(@ModelAttribute Image image){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        image.setCreditedArtist("profileImage");
        image.setStudioName("profileImage");
        image.setIsProfileImage(true);
        imagesDao.save(image);
        return "redirect:/profile-page";
    }

}