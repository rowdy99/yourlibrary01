package com.subham.controller;

import com.subham.model.Anm;
import com.subham.model.Doc;
import com.subham.repository.AnmRepo;
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
public class AnmBookController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;
    @Autowired
    DocRepository docRepository;
    @Autowired
    AnmRepo anmRepo;

    @GetMapping("/anmupload")
    public String anmupload(Model model){
        ArrayList<Anm> oh = (ArrayList<Anm>) anmRepo.findAll();
        model.addAttribute("anm",oh);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same
        return "anmupload";
    }

    @GetMapping("/anmbooks")
    public String getAnmBookController(Model model)
    {
        ArrayList<Anm> oh=(ArrayList<Anm>)anmRepo.findAll();
        model.addAttribute("anm",oh);

        return "anmbooks";
    }

    @PostMapping("/anmbooks")
    public  String SaveBooks(Anm oh){

        oh.getAuthorName(); oh.setAuthorName();oh.getBookName();oh.setBookName();oh.getContent();oh.setContent();oh.getData();oh.setData();oh.getPrice();oh.setPrice();
        anmRepo.save(oh);

        System.out.println(oh);
        return "doc";
    }

    @GetMapping("/anm/delete/{id}")
    public String DeleteBooks(@PathVariable("id") int id){

        anmRepo.deleteById(id);
        docRepository.deleteById(id);


        return "anmupload";
    }
}
