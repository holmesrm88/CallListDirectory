package com.holmes.callList.service;

import com.holmes.callList.model.CallList;
import com.holmes.callList.model.Contact;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public interface ContactListService {
    List<Contact> getAllContacts();
    void createContact(Contact contact);
    void updateContact(long id, Contact contact);
    AtomicReference<Contact> getContact(long id) throws NoSuchFieldException;
    void deleteContact(long id);
    List<CallList> getCallList();
}
