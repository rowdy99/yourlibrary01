package com.subham.controller;

import com.subham.model.Biological;
import com.subham.model.Doc;
import com.subham.repository.BiologicalRepo;
import com.subham.repository.BooksRepo;
import com.subham.repository.DocRepository;
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
public class BiologicalController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    BiologicalRepo biologicalRepo;

    @GetMapping("/biologicalupload")
    public String biologicalupload(Model model){
        ArrayList<Biological> oe = (ArrayList<Biological>) biologicalRepo.findAll();
        model.addAttribute("biological",oe);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same

        return "biologicalupload";
    }

    @GetMapping("/biologicalbooks")
    public String getBiologicalController(Model model)
    {
        ArrayList<Biological> oe=(ArrayList<Biological>)biologicalRepo.findAll();
        model.addAttribute("biological",oe);

        return "biologicalbooks";
    }

    @PostMapping("/biologicalbooks")
    public  String SaveBooks(Biological oe){

        oe.getAuthorName(); oe.setAuthorName();oe.getBookName();oe.setBookName();oe.getContent();oe.setContent();oe.getData();oe.setData();oe.getPrice();oe.setPrice();
        biologicalRepo.save(oe);




        System.out.println(oe);
        return "doc";
    }
    @GetMapping("/biological/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        biologicalRepo.deleteById(id);
        docRepository.deleteById(id);


        return "biologicalupload";
    }
}
