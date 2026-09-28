package com.scm.controllers;

import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.User;
import com.scm.helper.Helper;
import com.scm.services.UserService;

@Controller 
@RequestMapping("/user")
public class UserController {

      private Logger logger = LoggerFactory.getLogger(this.getClass());
      @Autowired 
      private UserService userService;

      
      // user dashboard page
      @RequestMapping(value ="/dashboard",method=RequestMethod.GET)
       public String userDashboard(){
         return "user/dashboard";
       }

       @RequestMapping(value ="/profile",method=RequestMethod.GET)
       public String userProfile(Model model,Authentication authentication){
          // String name = principal.getName();
                return "user/profile";
       }
}
