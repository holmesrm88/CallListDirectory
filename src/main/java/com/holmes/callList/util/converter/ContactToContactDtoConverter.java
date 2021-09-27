package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.model.Contact;

public class ContactToContactDtoConverter {

    //TODO add phone
    public ContactDTO contactToContactDtoConverter(Contact c){
        ContactDTO dto = new ContactDTO();

        dto.setFirstName(c.getName().getFirst() == null ? null : c.getName().getFirst());
        dto.setLastName(c.getName().getLast() == null ? null : c.getName().getLast());
        dto.setMiddleName(c.getName().getMiddle() == null ? null : c.getName().getMiddle());

        dto.setStreet(c.getAddress().getStreet() == null ? null : c.getAddress().getStreet());
        dto.setCity(c.getAddress().getCity() == null ? null : c.getAddress().getStreet());
        dto.setState(c.getAddress().getState() == null ? null : c.getAddress().getState());
        dto.setZip(c.getAddress().getZip() ==  null ? null : c.getAddress().getZip());

        dto.setEmail(c.getEmail() == null ? null : c.getEmail());

        return dto;
    }
}
