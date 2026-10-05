package com.scm.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.scm.entities.Contact;

import com.scm.helper.ResourceNotFoundException;
import com.scm.repositories.ContactRepo;
import com.scm.services.ContactService;
@Service 
public class ContactServiceImpl implements ContactService {
    
    private final ContactRepo repo;

    ContactServiceImpl(ContactRepo repo) {
        this.repo = repo;
    }

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
       
        contact.setId(contactId);
         return repo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        return contact;
       
    }

    @Override
    public List<Contact> getAll() {
        return repo.findAll();
    }
    @Override
    public Contact getById(String id) {
        return repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with given id"));
    }

    @Override
    public void delete(String id) {
        var contact =  repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact not found with given id"));
         repo.delete(contact);
       
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return null;
        
         
    }

    @Override
    public List<Contact> getByUserId(String userId) {
       return repo.findByUserId(userId);
        
    }

}
