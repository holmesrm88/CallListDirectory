package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.Address;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Name;
import com.holmes.callList.model.Phone;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ContactDtoToContactConverterTest {
    Contact contact;
    Name name;
    List<Phone> phone;
    Address address;
    ContactDTO contactDTO;
    PhoneDTO phoneDTO;
    List<PhoneDTO> phoneDTOS;

    @InjectMocks
    ContactDtoToContactConverter converter;

    @Before
    public void setup(){
        converter = new ContactDtoToContactConverter();
        contact = new Contact();
        name = new Name();
        phone = new ArrayList<>();
        address = new Address();

        contactDTO = new ContactDTO();
        contactDTO.setLastName("TestLastName");
        contactDTO.setFirstName("Generic");
        contactDTO.setEmail("test@testemail.com");

        name = new Name();
        name.setLast(contactDTO.getLastName());
        name.setFirst(contactDTO.getFirstName());

        phoneDTOS = new ArrayList<>();
        phoneDTO = new PhoneDTO();
        phoneDTO.setPhoneType("home");
        phoneDTO.setPhoneNumber("888-555-1212");
        phoneDTO.setPhone_id(1L);
        phoneDTO.setContact_id(contactDTO);
        phoneDTOS.add(phoneDTO);

        contactDTO.setPhone(phoneDTOS);
    }

    @Test
    public void testConverter(){
        Contact result = converter.convertFromContactDtoToContact(contactDTO);
        Assert.assertEquals("test@testemail.com",result.getEmail());
    }
}
