package com.subham.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String index(Model model) {
		return "index";
	}
	
	@GetMapping("login")
	public String login(Model model, HttpServletRequest request) {
		return "login";
	}
	
}
