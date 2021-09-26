package com.holmes.callList.service.Impl;

import com.holmes.callList.model.Contact;
import com.holmes.callList.repository.CallListRepository;
import com.holmes.callList.service.ContactListService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log
@Service
public class ContactListServiceImpl implements ContactListService {
    CallListRepository repository;

    @Autowired
    public ContactListServiceImpl(CallListRepository repository){
        this.repository = repository;
    }

    public ContactListServiceImpl() { }

    public List<Contact> getAllContacts(){
        log.info("ContactListServiceImpl | getAllContacts | START");
        List<Contact> contacts = repository.findAll();
        log.info("number of contacts returned: " + contacts.size());
        log.info("ContactListServiceImpl | getAllContacts | END");
        return contacts;
    }

    public void createContact(Contact contact){
        log.info("ContactListServiceImpl | createContact | START");
        repository.save(contact);
        log.info("ContactListServiceImpl | createContact | END");
    }

    public void updateContact(Contact contact){
        log.info("ContactListServiceImpl | updateContact | START");
        log.info("ContactListServiceImpl | updateContact | updating contact " + contact.getName());
        Contact con = repository.getById(contact.getId());
        con = update(con, contact);
        repository.save(con);
        log.info("ContactListServiceImpl | updateContact | END");
    }

    public Optional<Contact> getContact(long id){
        log.info("ContactListServiceImpl | getContact | START");
        log.info("ContactListServiceImpl | getContact | retrieving contact id: " + id);
        log.info("ContactListServiceImpl | getContact | END");
        return repository.findById(id);
    }

    public void deleteContact(long id){
        log.info("ContactListServiceImpl | deleteContact | START");
        log.info("ContactListServiceImpl | deleteContact | deleting contact id: " + id);
        log.info("ContactListServiceImpl | deleteContact | END");
        repository.deleteById(id);
    }

    public List<Contact> getCallList(){
        log.info("ContactListServiceImpl | getCallList | START");
        List<Contact> contacts = repository.getCallList();
        log.info("ContactListServiceImpl | getCallList | END");
        return contacts;
    }

    private Contact update(Contact oldCon, Contact newCon) {
        oldCon.setAddress(newCon.getAddress());
        oldCon.setEmail(newCon.getEmail());
        oldCon.setName(newCon.getName());
        oldCon.setPhone(newCon.getPhone());
        return oldCon;
    }
}
