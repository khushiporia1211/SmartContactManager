package com.scm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.forms.UserForm;




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
    public String register(Model model){

        UserForm userForm = new UserForm();
        userForm.setName("khushi");
        userForm.setAbout("writr something about yourself");
        model.addAttribute("userForm",userForm);
        return "register";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    // processing register request
   @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(Model model){
        System.out.println("processing request");
        //fetch form data

        return "redirect:/register";
    }

}
