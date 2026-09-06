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

    //about route
     @RequestMapping("/about")
     public String aboutPage(){
        System.out.println("abour page loading");
        return "about";
     }

     //service route
     @RequestMapping("/services")
     public String servicePage(){
        System.out.println("service page loading");
        return "services";
     }
     
     @RequestMapping("/login")
     public String userLogin(){
      System.out.println("login page loading");
      return "login";
     }

     @RequestMapping("/register")
     public String register(){
      System.out.println("register page loading");
      return "register";
     }

     @RequestMapping("/contact")
     public String contact(){
      System.out.println("contact page loading");
      return "contact";
     }
}
