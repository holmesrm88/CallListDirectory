package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.Address;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Name;
import com.holmes.callList.model.Phone;

import java.util.ArrayList;
import java.util.List;

public class ContactDtoToContactConverter {

    public Contact convertFromContactDtoToContact(ContactDTO contactDTO){
        Contact contact = new Contact();
        Name name = new Name();
        List<Phone> phone = new ArrayList<>();
        Address address = new Address();
        List<PhoneDTO> pdto = contactDTO.getPhone();

        name.setFirst(contactDTO.getFirstName() == null ? null : contactDTO.getFirstName());
        name.setMiddle(contactDTO.getMiddleName() == null ? null : contactDTO.getMiddleName());
        name.setLast(contactDTO.getLastName() == null ? null : contactDTO.getLastName());

        for(PhoneDTO pto : pdto){
            Phone p1 = new Phone();
            p1.setNumber(pto.getPhoneNumber() == null ? null : pto.getPhoneNumber());
            p1.setType(pto.getPhoneType() == null ? null : pto.getPhoneType());
            phone.add(p1);
        }

        address.setStreet(contactDTO.getStreet() == null ? null : contactDTO.getStreet());
        address.setCity(contactDTO.getCity() == null ? null : contactDTO.getCity());
        address.setState(contactDTO.getState() == null ? null : contactDTO.getState());
        address.setZip(contactDTO.getZip() == null ? null : contactDTO.getZip());

        contact.setPhone(phone);
        contact.setName(name);
        contact.setAddress(address);
        contact.setEmail(contactDTO.getEmail());
        return contact;
    }
}
