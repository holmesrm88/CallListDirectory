package com.holmes.callList.service.Impl;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.model.Contact;
import com.holmes.callList.repository.CallListRepository;
import com.holmes.callList.service.ContactListService;
import com.holmes.callList.util.converter.ContactDtoToContactConverter;
import com.holmes.callList.util.converter.ContactToContactDtoConverter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Log
@Service
public class ContactListServiceImpl implements ContactListService {
    CallListRepository repository;

    ContactDtoToContactConverter contactDtoToContactConverter;

    @Autowired
    public ContactListServiceImpl(CallListRepository repository){
        this.repository = repository;
    }

    public ContactListServiceImpl() { }

    public List<Contact> getAllContacts(){
        log.info("ContactListServiceImpl | getAllContacts | START");
        List<ContactDTO> contactDTOS= repository.findAll();
        List<Contact> contacts = new ArrayList<>();
        contactDtoToContactConverter = new ContactDtoToContactConverter();
        for(ContactDTO c : contactDTOS){
            Contact contact = contactDtoToContactConverter.convertFromContactDtoToContact(c);
            contacts.add(contact);
        }
        log.info("number of contacts returned: " + contacts.size());
        log.info("ContactListServiceImpl | getAllContacts | END");
        return contacts;
    }

    public void createContact(Contact contact){
        log.info("ContactListServiceImpl | createContact | START");
        ContactToContactDtoConverter converter = new ContactToContactDtoConverter();
        ContactDTO dto = converter.contactToContactDtoConverter(contact);
        repository.save(dto);
        log.info("ContactListServiceImpl | createContact | END");
    }

    public void updateContact(long id, Contact contact){
        log.info("ContactListServiceImpl | updateContact | START");
        log.info("ContactListServiceImpl | updateContact | updating contact " + contact.getName().getLast() + " Id " + contact.getId());
        ContactDTO conDto = repository.getById(id);
        conDto = update(conDto, contact);
        repository.save(conDto);
        log.info("ContactListServiceImpl | updateContact | END");
    }

    public AtomicReference<Contact> getContact(long id) throws NoSuchFieldException {
        log.info("ContactListServiceImpl | getContact | START");
        log.info("ContactListServiceImpl | getContact | re  trieving contact id: " + id);
        log.info("ContactListServiceImpl | getContact | END");
        contactDtoToContactConverter = new ContactDtoToContactConverter();
        Optional<ContactDTO> c = repository.findById(id);
        AtomicReference<Contact> contact = new AtomicReference<>(new Contact());
        c.ifPresentOrElse(
                (value) -> {
                    contact.set(contactDtoToContactConverter.convertFromContactDtoToContact(value));
                } ,
                () -> {
                    try {
                        throw new NoSuchFieldException();
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
        );
        return contact;
    }

    public void deleteContact(long id){
        log.info("ContactListServiceImpl | deleteContact | START");
        log.info("ContactListServiceImpl | deleteContact | deleting contact id: " + id);
        log.info("ContactListServiceImpl | deleteContact | END");
        repository.deleteById(id);
    }

    public List<Contact> getCallList(){
        log.info("ContactListServiceImpl | getCallList | START");
        List<ContactDTO> contactDTOS = repository.getCallList();
        List<Contact> contacts = new ArrayList<>();
        contactDtoToContactConverter = new ContactDtoToContactConverter();
        for(ContactDTO c : contactDTOS){
            Contact contact = contactDtoToContactConverter.convertFromContactDtoToContact(c);
            contacts.add(contact);
        }
        log.info("ContactListServiceImpl | getCallList | END");
        return contacts;
    }

    // TODO add phone
    private ContactDTO update(ContactDTO oldCon, Contact newCon) {
        oldCon.setEmail(newCon.getEmail() == null ? null : newCon.getEmail());

        oldCon.setFirstName(newCon.getName().getFirst() == null ? null : newCon.getName().getFirst());
        oldCon.setMiddleName(newCon.getName().getMiddle() == null ? null : newCon.getName().getMiddle());
        oldCon.setLastName(newCon.getName().getLast() == null ? null : newCon.getName().getLast());

        oldCon.setStreet(newCon.getAddress().getStreet() == null ? null : newCon.getAddress().getStreet());
        oldCon.setState(newCon.getAddress().getState() == null ? null : newCon.getAddress().getState());
        oldCon.setCity(newCon.getAddress().getCity() == null ? null : newCon.getAddress().getCity());
        oldCon.setZip(newCon.getAddress().getZip() == null ? null : newCon.getAddress().getZip());


        return oldCon;
    }
}
