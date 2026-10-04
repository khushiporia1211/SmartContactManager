package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.scm.entities.Contact;
import com.scm.entities.User;
import com.scm.forms.ContactForm;
import com.scm.helper.Helper;
import com.scm.helper.Message;
import com.scm.helper.MessageType;
import com.scm.services.ContactService;
import com.scm.services.ImageService;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;



@Controller 
@RequestMapping("/user/contacts")
public class ContactController {

    private final ImageService imageService;
    
    private final  UserService userService;
    private final ContactService contactService;

    ContactController(ContactService contactService, UserService userService,ImageService imageService) {
        this.contactService = contactService;
        this.userService = userService;
        this.imageService=imageService;
    }

    private Logger logger = LoggerFactory.getLogger(ContactController.class);

    //add contact page handler
    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm",contactForm);
        contactForm.setFavourite(true);
        return "user/add_contact";
    }

    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String saveContact(@Valid  @ModelAttribute ContactForm contactForm,BindingResult result,Authentication authentication,HttpSession session){
        // form --> contact
        String username = Helper.getEmailOfLoggedInUser(authentication);

       User user =  userService.getUserByEmail(username);

       //image process
       logger.info("file information : {}",contactForm.getContactImage().getOriginalFilename());

       //validate the form
       if(result.hasErrors()){
        session.setAttribute("message", Message.builder()
        .content("please correct the following errors")
        .type(MessageType.red)
        .build());
        return "user/add_contact";
       }
       //image uplaod process
       String fileURL = imageService.uploadImage(contactForm.getContactImage());
       

       

        Contact contact = new Contact();
        contact.setName(contactForm.getName()); 
        contact.setFavourite(contactForm.isFavourite()); 
        contact.setEmail(contactForm.getEmail()); 
        contact.setPhoneNumber(contactForm.getPhoneNumber()); 
        contact.setAddress(contactForm.getAddress()); 
        contact.setDescription(contactForm.getDescription()); 
        contact.setLinkedInLink(contactForm.getLinkedInLink()); 
        contact.setWebsiteLink(contactForm.getWebsiteLink()); 
        contact.setUser(user);
        contact.setPicture(fileURL);


        //process the form data
        contactService.save(contact);
        System.out.println(contactForm);

        //set the contact picture to the user

        //set message to be displayed on the view 
       session.setAttribute("message",Message.builder()
                                    .content("You have Successfully added a new content")
                                    .type(MessageType.green)
                                    .build() );
        return "redirect:/user/contacts/add";
    }
}
