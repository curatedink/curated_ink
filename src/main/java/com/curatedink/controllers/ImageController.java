package com.curatedink.controllers;

import com.curatedink.models.Image;
import com.curatedink.models.User;
import com.curatedink.repositories.ImageRepo;
import com.curatedink.repositories.StyleRepo;
import com.curatedink.repositories.UserRepo;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ImageController {

    private final ImageRepo imagesDao;
    private final UserRepo usersDao;
    private final StyleRepo stylesDao;

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


    // json
    @GetMapping(value = "/gallery.json")
    public @ResponseBody List<Image> getAllImagesInJSONFormat() {
        return imagesDao.findAllByIsProfileImageIsFalse();
    }

    @GetMapping("/gallery")
    public String getAllImagesWithAjax() {
        return "tattoos/gallery";
    }


//    @RequestMapping(value = "/gallery.json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<Image> getAllImagesInJSONFormat() {
//        return imagesDao.findAllByIsProfileImageIsFalse();
//    }


    //   delete image
    @PostMapping("/tattoos/delete/{id}")
    public String deleteImage(@PathVariable("id") long id) {
        Image imageToDelete = imagesDao.getOne(id);
        imagesDao.delete(imageToDelete);
        return "redirect:/profile-page";
    }


    // create artist image
    @GetMapping("tattoos/artist-upload")
    public String create(Model vModel) {
        vModel.addAttribute("image", new Image());
        return "tattoos/artist-upload";
    }

    // save artist image to images table
    @PostMapping("/artist-upload")
    public String insert(@ModelAttribute Image image) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        imagesDao.save(image);
        return "redirect:/profile-page";
    }


    // create canvas image
    @GetMapping("tattoos/canvas-upload")
    public String createCanvasImage(Model vModel) {
        vModel.addAttribute("image", new Image());
        return "tattoos/canvas-upload";
    }

    // save canvas image to images table
    @PostMapping("/canvas-upload")
    public String insertCanvasImage(@ModelAttribute Image image) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User author = usersDao.getOne(principal.getId());
        image.setUser(author);
        imagesDao.save(image);
        return "redirect:/profile-page";
    }


    // edit canvas image
    @GetMapping("/canvas-image-edit/{id}")
    public String editCanvasImage(@PathVariable("id") long id, Model model) {
        Image imageToEdit = imagesDao.getOne(id);
        model.addAttribute("image", imageToEdit);
        return "tattoos/canvas-image-edit";
    }
    // save canvas image edit
    @PostMapping("/tattoos/canvas-image-edit/{id}")
    public String updateCanvasImage(@ModelAttribute Image imageToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        imageToEdit.setUser(currentUser);
        imagesDao.save(imageToEdit);
        return "redirect:/profile-page";
    }


    // edit artist image
    @GetMapping("/artist-image-edit/{id}")
    public String editArtistImage(@PathVariable("id") long id, Model model) {
        Image imageToEdit = imagesDao.getOne(id);
        model.addAttribute("image", imageToEdit);
        return "tattoos/artist-image-edit";
    }

    // save artist image edit
    @PostMapping("/tattoos/artist-image-edit/{id}")
    public String updateArtistImage(@ModelAttribute Image imageToEdit) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        imageToEdit.setUser(currentUser);
        imagesDao.save(imageToEdit);
        return "redirect:/profile-page";
    }


}