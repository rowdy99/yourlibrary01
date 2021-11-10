package com.subham.controller;

import com.subham.model.*;
import com.subham.repository.*;
import com.subham.service.DocStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

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
    @Autowired
    EngineeringRepo engineeringRepo;
    @Autowired
    BiologicalRepo biologicalRepo;
    @Autowired
    ChemistryRepo chemistryRepo;
//    @Autowired
//    MathRepo mathRepo;
    @Autowired
    AnmRepo anmRepo;
    @Autowired
    GnmRepo gnmRepo;
    @Autowired
    BscRepo bscRepo;







    @GetMapping("/books")
    public String getBookController(Model model)
    {
        ArrayList<Books> ob=(ArrayList<Books>)booksRepo.findAll();
        model.addAttribute("data",ob);

        ArrayList<Computer> oa=(ArrayList<Computer>)computerRepo.findAll();
        model.addAttribute("computer",oa);

        ArrayList<Science> oc=(ArrayList<Science>)scienceRepo.findAll();
        model.addAttribute("science",oc);

        ArrayList<Engineering> od=(ArrayList<Engineering>)engineeringRepo.findAll();
        model.addAttribute("engineering",od);

        ArrayList<Biological> oe=(ArrayList<Biological>)biologicalRepo.findAll();
        model.addAttribute("biological",oe);

        ArrayList<Chemistry> of=(ArrayList<Chemistry>)chemistryRepo.findAll();
        model.addAttribute("chemistry",of);

//        ArrayList<Math> og=(ArrayList<Math>)mathRepo.findAll();
//        model.addAttribute("math",og);

        ArrayList<Anm> oh=(ArrayList<Anm>)anmRepo.findAll();
        model.addAttribute("anm",oh);

        ArrayList<Gnm> oi=(ArrayList<Gnm>)gnmRepo.findAll();
        model.addAttribute("gnm",oi);

        ArrayList<Bsc> oj=(ArrayList<Bsc>)bscRepo.findAll();
        model.addAttribute("bsc",oj);

        return "books";
    }





    @PostMapping("/books")
    public  String SaveBooks(Books ob){

        ob.getAuthorName(); ob.setAuthorName();ob.getBookName();ob.setBookName();ob.getContent();ob.setContent();ob.getData();ob.setData();ob.getPrice();ob.setPrice();
        booksRepo.save(ob);

       System.out.println(ob);
        return "doc";
    }






    @GetMapping("/search")
    public String Search(HttpServletRequest request, Model m)
    {
           String name= request.getParameter("search");
           List<Books> ob=booksRepo.findAllByBookNameContaining(name);
        m.addAttribute("data",ob);

        List<Computer> oa=computerRepo.findAllByBookNameContaining(name);
        m.addAttribute("computer",oa);

        List<Science> oc=scienceRepo.findAllByBookNameContaining(name);
        m.addAttribute("science",oc);

        List<Engineering> od=engineeringRepo.findAllByBookNameContaining(name);
        m.addAttribute("engineering",od);

        List<Biological> oe=biologicalRepo.findAllByBookNameContaining(name);
        m.addAttribute("biological",oe);

        List<Chemistry> of=chemistryRepo.findAllByBookNameContaining(name);
        m.addAttribute("chemistry",of);

//        List<Math> og=mathRepo.findAllByBookNameContaining(name);
//        m.addAttribute("math",og);

        List<Anm> oh=anmRepo.findAllByBookNameContaining(name);
        m.addAttribute("anm",oh);

        List<Gnm> oi=gnmRepo.findAllByBookNameContaining(name);
        m.addAttribute("gnm",oi);

        List<Bsc> oj=bscRepo.findAllByBookNameContaining(name);
        m.addAttribute("bsc",oj);

        List<Doc> docs2 = docStorageService.getFiles();
        m.addAttribute("docs", docs2);

        System.out.println(ob);

        return "search";

    }

}
