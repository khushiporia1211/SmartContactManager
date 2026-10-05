package com.scm.helper;


import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailOfLoggedInUser(Authentication authentication){
    //    AuthenticationPrincipal principal = (AuthenticationPrincipal)authentication.getPrincipal();
       
        if(authentication instanceof OAuth2AuthenticationToken){

            var oauth2AuthenticationToken = (OAuth2AuthenticationToken)authentication;

            var clientId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oAuth2User = (OAuth2User)authentication.getPrincipal();
            String username = "";

            if(clientId.equalsIgnoreCase("google")){
                //sign with google
                System.out.println("getting email from google");
               username =  oAuth2User.getAttribute("email").toString();

            }else if(clientId.equalsIgnoreCase("github")){
                 //sign with github
                 System.out.println("getting email from github");
                   username = oAuth2User.getAttribute("email") !=null? oAuth2User.getAttribute("email").toString()
                    :oAuth2User.getAttribute("login").toString()+"@gmail.com";

            }
            return username;

        

       
        }
         //agar email id password se login kiya ho to 
         else{
            System.out.println("getting data from localdatabase");
            return authentication.getName();
         }
       
    }

}
