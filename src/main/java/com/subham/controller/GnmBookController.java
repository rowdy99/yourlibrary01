package com.subham.controller;


import com.subham.model.Doc;
import com.subham.model.Gnm;
import com.subham.repository.BooksRepo;
import com.subham.repository.DocRepository;
import com.subham.repository.GnmRepo;
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
public class GnmBookController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    GnmRepo gnmRepo;

    @GetMapping("/gnmupload")
    public String gnmupload(Model model){
        ArrayList<Gnm> oi = (ArrayList<Gnm>) gnmRepo.findAll();
        model.addAttribute("gnm",oi);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same
        return "gnmupload";
    }

    @GetMapping("/gnmbooks")
    public String getGnmBookController(Model model)
    {
        ArrayList<Gnm> oi=(ArrayList<Gnm>)gnmRepo.findAll();
        model.addAttribute("gnm",oi);

        return "gnmbooks";
    }

    @PostMapping("/gnmbooks")
    public  String SaveBooks(Gnm oi){

        oi.getAuthorName(); oi.setAuthorName();oi.getBookName();oi.setBookName();oi.getContent();oi.setContent();oi.getData();oi.setData();oi.getPrice();oi.setPrice();
        gnmRepo.save(oi);

        System.out.println(oi);
        return "doc";
    }

    @GetMapping("/gnm/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        gnmRepo.deleteById(id);
        docRepository.deleteById(id);


        return "gnmupload";
    }
}
