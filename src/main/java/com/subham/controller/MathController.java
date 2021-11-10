//package com.subham.controller;
//
//import com.subham.model.Doc;
//import com.subham.repository.BooksRepo;
//import com.subham.repository.DocRepository;
//import com.subham.repository.MathRepo;
//import com.subham.service.DocStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//public class MathController {
//    @Autowired
//    BooksRepo booksRepo;
//    @Autowired
//    DocStorageService docStorageService;
//    @Autowired
//    DocRepository docRepository;
//    @Autowired
//    MathRepo mathRepo;
//
//    @GetMapping("/mathupload")
//    public String mathupload(Model model){
//        ArrayList<Math> og = (ArrayList<Math>) mathRepo.findAll();
//        model.addAttribute("math",og);
//        List<Doc> docs2 = docStorageService.getFiles();
//        model.addAttribute("docs", docs2); //attribute name should be same
//        return "mathupload";
//    }
//
//    @GetMapping("/mathbooks")
//    public String getMathController(Model model)
//    {
//        ArrayList<Math> og=(ArrayList<Math>)mathRepo.findAll();
//        model.addAttribute("math",og);
//
//        return "mathbooks";
//    }
//
//    @PostMapping("/mathbooks")
//    public  String SaveBooks(Math og){
//
//        mathRepo.save(og);
//
//
//
//
//        System.out.println(og);
//        return "doc";
//    }
//
//    @GetMapping("/math/delete/{id}")
//    public String DeleteBooks(@PathVariable("id") int id){
//
//        mathRepo.deleteById(id);
//        docRepository.deleteById(id);
//
//
//        return "mathupload";
//    }
//}
