package com.holmes.callList.controller;

import com.holmes.callList.model.Contact;
import com.holmes.callList.service.ContactListService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@ComponentScan
@RestController
@Log
public class CallListController {

    @Autowired
    ContactListService contactListService;

    @GetMapping("/contacts")
    public List<Contact> getAllContacts(){
        return contactListService.getAllContacts();
    }

    @PostMapping("/contacts")
    public ResponseEntity createContact(@RequestBody Contact contact) {
        contactListService.createContact(contact);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/contacts/{id}")
    public ResponseEntity updateContact(@RequestBody Contact contact) {
        contactListService.updateContact(contact);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public Optional<Contact> getContact(@PathVariable long id) {
        Optional<Contact> con = contactListService.getContact(id);
        return con;
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity deleteContact(@PathVariable long id) {
        contactListService.deleteContact(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/contacts/call-list")
    public List<Contact> getCallList(){
        return contactListService.getCallList();
    }


}
