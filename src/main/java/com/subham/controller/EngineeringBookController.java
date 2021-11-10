package com.subham.controller;

import com.subham.model.Doc;
import com.subham.model.Engineering;
import com.subham.repository.*;
import com.subham.service.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EngineeringBookController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    EngineeringRepo engineeringRepo;

    @GetMapping("/engineeringupload")
    public String engineeringupload(Model model){
        ArrayList<Engineering> od = (ArrayList<Engineering>) engineeringRepo.findAll();
        model.addAttribute("engineering",od);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same


        return "engineeringupload";
    }

    @GetMapping("/engineeringbooks")
    public String getEngineeringBookController(Model model)
    {
        ArrayList<Engineering> od=(ArrayList<Engineering>)engineeringRepo.findAll();
        model.addAttribute("engineering",od);

        return "engineeringbooks";
    }

    @PostMapping("/engineeringbooks")
    public  String SaveBooks(Engineering od){

        od.getAuthorName(); od.setAuthorName();od.getBookName();od.setBookName();od.getContent();od.setContent();od.getData();od.setData();od.getPrice();od.setPrice();
        engineeringRepo.save(od);




        System.out.println(od);
        return "doc";
    }
    @GetMapping("/engineering/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        engineeringRepo.deleteById(id);
        docRepository.deleteById(id);


        return "engineeringupload";
    }
}
