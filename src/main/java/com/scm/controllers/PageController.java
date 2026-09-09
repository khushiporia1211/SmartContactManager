package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PageController {

   
     @RequestMapping("/home")
    public String home(Model model){
        System.out.println("Home Page Handler");
         // sending data from controller to view using thymeleaf
        model.addAttribute("name","Substring Technologies");
        return "home";

    }

    @RequestMapping("/about")
    public String aboutPage(){
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage(){
        return "services";
    }

    @RequestMapping("/login")
    public String userLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

}
