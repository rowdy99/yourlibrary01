package com.subham.controller;

import com.subham.model.ImageGallery;
import com.subham.repository.ImageGalleryRepository;
import com.subham.service.ImageGalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class FeedbackController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Value("${uploadDir}")
    private String uploadFolder;
    @Autowired
    private ImageGalleryService imageGalleryService;
    @Autowired
    private ImageGalleryRepository imageGalleryRepository;

    @GetMapping(value = {"/uploadphoto"})
    public String addProductPage() {

        return "uploadphoto";
    }

    @PostMapping("/image/saveImageDetails")
    public @ResponseBody
    ResponseEntity<?> createProduct(@RequestParam("name") String name,
                                    @RequestParam("rating") Long rating, @RequestParam("description") String description, Model model, HttpServletRequest request
            , final @RequestParam("image") MultipartFile file) {
        try {
            //String uploadDirectory = System.getProperty("user.dir") + uploadFolder;
            String uploadDirectory = request.getServletContext().getRealPath(uploadFolder);
            log.info("uploadDirectory:: " + uploadDirectory);
            String fileName = file.getOriginalFilename();
            String filePath = Paths.get(uploadDirectory, fileName).toString();
            log.info("FileName: " + file.getOriginalFilename());
            if (fileName == null || fileName.contains("..")) {
                model.addAttribute("invalid", "Sorry! Filename contains invalid path sequence \" + fileName");
                return new ResponseEntity<>("Sorry! Filename contains invalid path sequence " + fileName, HttpStatus.BAD_REQUEST);
            }
            String[] names = name.split(",");
            String[] descriptions = description.split(",");
            Date createDate = new Date();
            log.info("Name: " + names[0] + " " + filePath);
            log.info("description: " + descriptions[0]);
            log.info("rating: " + rating);
            try {
                File dir = new File(uploadDirectory);
                if (!dir.exists()) {
                    log.info("Folder Created");
                    dir.mkdirs();
                }
                // Save the file locally
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                stream.write(file.getBytes());
                stream.close();
            } catch (Exception e) {
                log.info("in catch");
                e.printStackTrace();
            }
            byte[] imageData = file.getBytes();
            ImageGallery imageGallery = new ImageGallery();
            imageGallery.setName(names[0]);
            imageGallery.setImage(imageData);
            imageGallery.setRating(rating);
            imageGallery.setDescription(descriptions[0]);
            imageGallery.setCreateDate(createDate);
            imageGalleryService.saveImage(imageGallery);

            log.info("HttpStatus===" + new ResponseEntity<>(HttpStatus.OK));
            return new ResponseEntity<>("Photo Saved With File - " + fileName, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception: " + e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/image/display/{id}")
    @ResponseBody
    void showImage(@PathVariable("id") Long id, HttpServletResponse response, Optional<ImageGallery> imageGallery)
            throws ServletException, IOException {
        log.info("Id :: " + id);
        imageGallery = imageGalleryService.getImageById(id);
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(imageGallery.get().getImage());
        response.getOutputStream().close();


    }

    @GetMapping("/images")
    String show(Model map) {
        List<ImageGallery> images = imageGalleryService.getAllActiveImages();
        map.addAttribute("images", images);


        return "images";
    }

    @GetMapping("/image/imageDetails")
    String showProductDetails(@RequestParam("id") Long id, Optional<ImageGallery> imageGallery,Model model) {
        try {
            log.info("Id :: " + id);
            if (id != 0) {
                imageGallery = imageGalleryService.getImageById(id);

                log.info("products :: " + imageGallery);
                if (imageGallery.isPresent()) {
                    model.addAttribute("id", imageGallery.get().getId());
                    model.addAttribute("description", imageGallery.get().getDescription());
                    model.addAttribute("name", imageGallery.get().getName());
                    model.addAttribute("rating", imageGallery.get().getRating());
                    return "imagedetails";
                }

                return "redirect:/";
            }
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/";
        }
    }
    @GetMapping("/deleteallimages")
    String deleteallimages(Model map) {
        List<ImageGallery> images = imageGalleryService.getAllActiveImages();
        map.addAttribute("images", images);


        return "deleteallimages";
    }
    @GetMapping("/deleteimages/{id}")
    public String DeleteImages(){
        imageGalleryRepository.deleteAll();
        return "redirect:/deleteallimages";
    }
}