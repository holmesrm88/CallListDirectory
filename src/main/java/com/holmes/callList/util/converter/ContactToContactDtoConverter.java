package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class ContactToContactDtoConverter {

    public ContactDTO contactToContactDtoConverter(Contact c){
        ContactDTO dto = new ContactDTO();

        dto.setFirstName(c.getName().getFirst() == null ? null : c.getName().getFirst());
        dto.setLastName(c.getName().getLast() == null ? null : c.getName().getLast());
        dto.setMiddleName(c.getName().getMiddle() == null ? null : c.getName().getMiddle());

        dto.setStreet(c.getAddress().getStreet() == null ? null : c.getAddress().getStreet());
        dto.setCity(c.getAddress().getCity() == null ? null : c.getAddress().getStreet());
        dto.setState(c.getAddress().getState() == null ? null : c.getAddress().getState());
        dto.setZip(c.getAddress().getZip() ==  null ? null : c.getAddress().getZip());

        List<PhoneDTO> pdtos = new ArrayList<>();
        List<Phone> phones = c.getPhone();

        for(Phone p : phones){
            PhoneDTO pdto = new PhoneDTO();
            pdto.setPhoneNumber(p.getNumber() == null ? null : p.getNumber());
            pdto.setPhoneType(p.getType() == null ? null : p.getType());
//            pdto.setContact_id(dto);
            pdtos.add(pdto);
            dto.addToPhoneDto(pdto);
        }

        //dto.setPhone(pdtos);

        dto.setEmail(c.getEmail() == null ? null : c.getEmail());

        return dto;
    }
}
