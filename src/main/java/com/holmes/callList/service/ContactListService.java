package com.holmes.callList.service;

import com.holmes.callList.model.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactListService {
    List<Contact> getAllContacts();
    void createContact(Contact contact);
    void updateContact(Contact contact);
    Optional<Contact> getContact(long id);
    void deleteContact(long id);
    List<Contact> getCallList();
}
