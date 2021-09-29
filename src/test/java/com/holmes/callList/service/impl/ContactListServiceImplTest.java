package com.holmes.callList.service.impl;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.*;
import com.holmes.callList.repository.ContactRepository;
import com.holmes.callList.repository.PhoneRepository;
import com.holmes.callList.service.Impl.ContactListServiceImpl;
import com.holmes.callList.util.converter.ContactDtoToContactConverter;
import com.holmes.callList.util.converter.PhoneDtoToCallListConverter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContactListServiceImplTest {

    List<PhoneDTO> phoneDTOS;
    PhoneDTO phoneDTO;
    ContactDTO contactDTO;
    List<ContactDTO> contactDTOS;
    Contact contact;
    List<Contact> contacts;
    Phone phone;
    List<Phone> phones;
    Name name;
    Address address;
    List<CallList> callLists;
    CallList callList;

    @InjectMocks
    private ContactListServiceImpl contactListService;

    @Mock
    ContactRepository contactRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Mock
    ContactDtoToContactConverter contactDtoToContactConverter;

    @Mock
    PhoneDtoToCallListConverter phoneDtoToCallListConverter;

    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);

        phoneDTOS = new ArrayList<>();
        phoneDTO = new PhoneDTO();
        phoneDTO.setPhoneType("home");
        phoneDTO.setPhoneNumber("888-555-1212");
        phoneDTO.setPhone_id(1L);
        phoneDTOS.add(phoneDTO);

        phones = new ArrayList<>();
        phone = new Phone();
        phone.setType("home");
        phone.setNumber("888-555-1212");
        phones.add(phone);

        contactDTOS = new ArrayList<>();
        contactDTO = new ContactDTO();
        contactDTO.setLastName("TestLastName");
        contactDTO.setFirstName("Generic");
        contactDTO.setStreet("555 Park Ave");
        contactDTO.setCity("New York City");
        contactDTO.setState("NY");
        contactDTO.setZip("23228");
        contactDTO.setEmail("testEmail@email.com");
        contactDTO.setPhone(phoneDTOS);
        contactDTO.setContact_id(1);
        contactDTOS.add(contactDTO);

        contacts = new ArrayList<>();
        contact = new Contact();
        name = new Name();
        address = new Address();

        name.setFirst(contactDTO.getFirstName());
        name.setMiddle(contactDTO.getMiddleName());
        name.setLast(contactDTO.getLastName());

        address.setZip(contactDTO.getZip());
        address.setCity(contactDTO.getZip());
        address.setState(contactDTO.getState());
        address.setStreet(contactDTO.getStreet());


        contact.setName(name);
        contact.setAddress(address);
        contact.setEmail(contactDTO.getEmail());
        contact.setPhone(phones);

        callLists = new ArrayList<>();
        callList = new CallList();
        callList.setName(name);
        callList.setPhoneNumber(contactDTO.getPhone().get(0).getPhoneNumber());
        callLists.add(callList);


        contactDtoToContactConverter = new ContactDtoToContactConverter();
        phoneDtoToCallListConverter = new PhoneDtoToCallListConverter();
        contactListService = new ContactListServiceImpl(contactRepository, phoneRepository);
    }

    @Test
    public void testGetAllContacts(){
        when(contactRepository.findAll()).thenReturn(contactDTOS);
        //when(contactDtoToContactConverter.convertFromContactDtoToContact(contactDTO)).thenReturn(contact);
        List<Contact> result = contactListService.getAllContacts();
        Assert.assertEquals(contact.getEmail(), result.get(0).getEmail());
        Assert.assertEquals(contact.getName().getLast(), result.get(0).getName().getLast());
    }

    @Test
    public void testCreateContact(){
        ContactDTO saved = new ContactDTO();
        saved.setContact_id(1);
        saved.setLastName("updatedName");
        when(contactRepository.save(contactDTO)).thenReturn(saved);
        long result = contactListService.createContact(contact);
        Assert.assertEquals(saved.getContact_id(), result);
    }

    @Test
    public void testGetContact(){
        Optional<ContactDTO> ct = Optional.ofNullable(contactDTO);
        when(contactRepository.findById(1L)).thenReturn(ct);
        AtomicReference<Contact> c1 = contactListService.getContact(1L);
        Assert.assertEquals(contact.getName().getLast(), c1.get().getName().getLast());
    }

    @Test
    public void testDeleteContact(){
        contactListService.deleteContact(1l);
        verify(contactRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetCallList(){
        when(phoneRepository.getCallList()).thenReturn(phoneDTOS);
        when(phoneDtoToCallListConverter.converter(phoneDTO)).thenReturn(callList);
        List<CallList> results = contactListService.getCallList();
        Assert.assertEquals(callList.getName().getLast(), results.get(0).getName().getLast());
    }

}
