package com.holmes.callList.service.Impl;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Phone;
import com.holmes.callList.repository.ContactRepository;
import com.holmes.callList.repository.PhoneRepository;
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
    ContactRepository contactRepository;
    PhoneRepository phoneRepository;

    ContactDtoToContactConverter contactDtoToContactConverter;

    @Autowired
    public ContactListServiceImpl(ContactRepository contactRepository, PhoneRepository phoneRepository){
        this.contactRepository = contactRepository;
        this.phoneRepository = phoneRepository;
    }

    public ContactListServiceImpl() { }

    public List<Contact> getAllContacts(){
        log.info("ContactListServiceImpl | getAllContacts | START");
        List<ContactDTO> contactDTOS= contactRepository.findAll();
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
        ContactDTO contactDTO = contactRepository.save(dto);
        log.info("ContactListServiceImpl | createContact | END");
    }

    public void updateContact(long id, Contact contact){
        log.info("ContactListServiceImpl | updateContact | START");
        log.info("ContactListServiceImpl | updateContact | updating contact " + contact.getName().getLast());
        ContactDTO conDto = contactRepository.getById(id);
        conDto = update(conDto, contact);
        contactRepository.save(conDto);
        log.info("ContactListServiceImpl | updateContact | END");
    }

    public AtomicReference<Contact> getContact(long id) {
        log.info("ContactListServiceImpl | getContact | START");
        log.info("ContactListServiceImpl | getContact | re  trieving contact id: " + id);
        log.info("ContactListServiceImpl | getContact | END");
        contactDtoToContactConverter = new ContactDtoToContactConverter();
        Optional<ContactDTO> c = contactRepository.findById(id);
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
        contactRepository.deleteById(id);
    }

    public List<Contact> getCallList(){
        log.info("ContactListServiceImpl | getCallList | START");
        List<ContactDTO> contactDTOS = phoneRepository.getCallList();
        List<Contact> contacts = new ArrayList<>();
        contactDtoToContactConverter = new ContactDtoToContactConverter();
        for(ContactDTO c : contactDTOS){
            Contact contact = contactDtoToContactConverter.convertFromContactDtoToContact(c);
            contacts.add(contact);
        }
        log.info("ContactListServiceImpl | getCallList | END");
        return contacts;
    }

    private ContactDTO update(ContactDTO oldCon, Contact newCon) {
        oldCon.setEmail(newCon.getEmail() == null ? null : newCon.getEmail());

        oldCon.setFirstName(newCon.getName().getFirst() == null ? null : newCon.getName().getFirst());
        oldCon.setMiddleName(newCon.getName().getMiddle() == null ? null : newCon.getName().getMiddle());
        oldCon.setLastName(newCon.getName().getLast() == null ? null : newCon.getName().getLast());

        oldCon.setStreet(newCon.getAddress().getStreet() == null ? null : newCon.getAddress().getStreet());
        oldCon.setState(newCon.getAddress().getState() == null ? null : newCon.getAddress().getState());
        oldCon.setCity(newCon.getAddress().getCity() == null ? null : newCon.getAddress().getCity());
        oldCon.setZip(newCon.getAddress().getZip() == null ? null : newCon.getAddress().getZip());

        List<PhoneDTO> pdtos = new ArrayList<>();
        List<Phone> phones = newCon.getPhone();

        for(Phone p : phones){
            PhoneDTO pdto = new PhoneDTO();
            pdto.setPhoneNumber(p.getNumber() == null ? null : p.getNumber());
            pdto.setPhoneType(p.getType() == null ? null : p.getType());
            pdtos.add(pdto);
            oldCon.addToPhoneDto(pdto);
        }
        oldCon.setPhone(pdtos);

        return oldCon;
    }
}
