package com.holmes.callList.controller;

import com.holmes.callList.model.CallList;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Phone;
import com.holmes.callList.service.ContactListService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
    public ResponseEntity updateContact(@PathVariable long id, @RequestBody Contact contact) {
        log.info("contactId in controller " + id);
        contactListService.updateContact(id,contact);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public AtomicReference<Contact> getContact(@PathVariable long id) throws NoSuchFieldException {
        AtomicReference<Contact> con = contactListService.getContact(id);
        return con;
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity deleteContact(@PathVariable long id) {
        contactListService.deleteContact(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/contacts/call-list")
    public List<CallList> getCallList(){
        return contactListService.getCallList();
    }


}
