package com.scm.services.impl;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.repositories.UserRepo;

@Service 
public class SecurityCustomUserDetailService implements UserDetailsService {
     private final UserRepo userRepo;

    SecurityCustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // apne user ko load krvana h
        return userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("user not found with this email: "+username));
    }

}
