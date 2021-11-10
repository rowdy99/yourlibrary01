package com.subham.controller;

import com.subham.model.Books;
import com.subham.model.Doc;
import com.subham.repository.BooksRepo;
import com.subham.service.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    BooksRepo booksRepo;
    @Autowired
    DocStorageService docStorageService;



    @GetMapping("admin")
    public String admin(Model model){
        ArrayList<Books> ob = (ArrayList<Books>) booksRepo.findAll();
        model.addAttribute("books",ob);
        List<Doc> docs2 = docStorageService.getFiles();
        model.addAttribute("docs", docs2); //attribute name should be same


        return "admin";
    }

    @GetMapping("/EditBooks")
    public  String SaveBooks( Books ob){
        System.out.println("hi");
        System.out.println(ob.toString());
        return "hi";
    }


}
