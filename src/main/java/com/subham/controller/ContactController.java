package com.subham.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller
public class ContactController {
@Autowired
private JavaMailSender mailSender;

    @GetMapping("/contact_form")
    public String showContactForm(){
        return "contact_form";
    }

    @PostMapping("/contact_form")
    public String submitContact(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String tel = request.getParameter("tel");
        String content = request.getParameter("content");


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);




        String mailSubject = fullName + "has sent a message";
        String mailContent = "<p><b>Sender Name:</b>" + fullName + "</p>";
        mailContent += "<p><b>Sender E-mail:</b>" + email + "</p>";
        mailContent += "<p><b>Sender telephone-no:</b>" + tel + "</p>";
        mailContent += "<p><b>Sender message:</b>" + content + "</p>";

        helper.setFrom("medeazynotice@gmail.com","medeazy");
        helper.setTo("medeazymessage@gmail.com");


        helper.setSubject(mailSubject);
        helper.setText(mailContent, true);

        mailSender.send(message);


        return "message";
    }

    @GetMapping("/message")
    public String message(){
        return "message";
    }

}
