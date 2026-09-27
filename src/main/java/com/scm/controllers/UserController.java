package com.scm.controllers;

import java.security.Principal;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.helper.Helper;

@Controller 
@RequestMapping("/user")
public class UserController {

      private Logger logger = LoggerFactory.getLogger(this.getClass());

        // user dashboard page

       @RequestMapping(value ="/dashboard",method=RequestMethod.GET)
       public String userDashboard(){
         return "user/dashboard";
       }

       @RequestMapping(value ="/profile",method=RequestMethod.GET)
       public String userProfile(Authentication authentication){

        // String name = principal.getName();
       String username =  Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User Logged in: {}",username);
         return "user/profile";
       }
}
