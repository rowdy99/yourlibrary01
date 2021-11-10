package com.subham.controller;

import com.subham.model.Chemistry;
import com.subham.model.Doc;
import com.subham.repository.BooksRepo;
import com.subham.repository.ChemistryRepo;
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
public class ChemistryController {

    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    ChemistryRepo chemistryRepo;

    @GetMapping("/chemistryupload")
    public String chemistryupload(Model model){
        ArrayList<Chemistry> of = (ArrayList<Chemistry>) chemistryRepo.findAll();
        model.addAttribute("chemistry",of);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same


        return "chemistryupload";
    }

    @GetMapping("/chemistrybooks")
    public String getChemistryController(Model model)
    {
        ArrayList<Chemistry> of=(ArrayList<Chemistry>)chemistryRepo.findAll();
        model.addAttribute("chemistry",of);

        return "chemistrybooks";
    }

    @PostMapping("/chemistrybooks")
    public  String SaveBooks(Chemistry of){

        of.getAuthorName(); of.setAuthorName();of.getBookName();of.setBookName();of.getContent();of.setContent();of.getData();of.setData();of.getPrice();of.setPrice();
        chemistryRepo.save(of);




        System.out.println(of);
        return "doc";
    }
    @GetMapping("/chemistry/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        chemistryRepo.deleteById(id);
        docRepository.deleteById(id);


        return "chemistryupload";
    }

}
