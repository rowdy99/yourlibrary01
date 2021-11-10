package com.subham.controller;

import com.subham.model.Doc;
import com.subham.model.Science;
import com.subham.repository.BooksRepo;
import com.subham.repository.ComputerRepo;
import com.subham.repository.DocRepository;
import com.subham.repository.ScienceRepo;
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
public class ScienceBookController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    ComputerRepo computerRepo;
    @Autowired
    ScienceRepo scienceRepo;


    @GetMapping("/scienceupload")
    public String scienceupload(Model model){
        ArrayList<Science> oc = (ArrayList<Science>) scienceRepo.findAll();
        model.addAttribute("science",oc);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same


        return "scienceupload";
    }

    @GetMapping("/sciencebooks")
    public String getScienceBookController(Model model)
    {
        ArrayList<Science> oc=(ArrayList<Science>)scienceRepo.findAll();
        model.addAttribute("science",oc);

        return "sciencebooks";
    }
    @PostMapping("/sciencebooks")
    public  String SaveBooks(Science oc ){

        oc.getAuthorName(); oc.setAuthorName();oc.getBookName();oc.setBookName();oc.getContent();oc.setContent();oc.getData();oc.setData();oc.getPrice();oc.setPrice();
        scienceRepo.save(oc);


        System.out.println(oc);
        return "doc";
    }

    @GetMapping("/science/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        scienceRepo.deleteById(id);
        docRepository.deleteById(id);


        return "scienceupload";
    }

}
