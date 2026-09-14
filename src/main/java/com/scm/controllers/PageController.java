package com.scm.controllers;

import java.lang.management.MemoryType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;




@Controller
public class PageController {
    private final UserService userService;

    PageController(UserService userService) {
        this.userService = userService;
    }

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
        userForm.setAbout("write something about yourself");
        model.addAttribute("userForm",userForm);
        return "register";
    }

    @RequestMapping("/contact")
    public String contact(){
        return "contact";
    }

    // processing register request
   @RequestMapping(value="/do-register", method=RequestMethod.POST)
    public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult,HttpSession session){
        System.out.println("processing request");
        //fetch form data
        System.out.println(userForm);

        //validate form data

        if(rBindingResult.hasErrors()){
            return "register";
        }

         //save to datebase
            //Userform---> User
        //  User user= User.builder()
        //  .name(userForm.getName())
        //  .email(userForm.getEmail())
        //  .password(userForm.getPassword())
        //  .about(userForm.getAbout())
        //  .phoneNumber(userForm.getPhoneNumber())
        //  .profilePic("https://www.dreamstime.com/default-profile-picture-icon-high-resolution-high-resolution-default-profile-picture-icon-symbolizing-no-display-picture-image360167031")
        //  .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.dreamstime.com/default-profile-picture-icon-high-resolution-high-resolution-default-profile-picture-icon-symbolizing-no-display-picture-image360167031");

         User savedUser = userService.saveUser((user));
         System.out.println("user saved");


         // message = "registration Successfully"
         // add  the message

        Message message =  Message.builder().content("Registration Successful").type(MessageType.blue).build();
         session.setAttribute("message",message);

        return "redirect:/register";


       
      
    }

}
