package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.Address;
import com.holmes.callList.model.Contact;
import com.holmes.callList.model.Name;
import com.holmes.callList.model.Phone;

public class ContactDtoToContactConverter {

    // TODO add phone
    public Contact convertFromContactDtoToContact(ContactDTO contactDTO){
        Contact contact = new Contact();
        Name name = new Name();
        Phone phone = new Phone();
        Address address = new Address();

        name.setFirst(contactDTO.getFirstName() == null ? null : contactDTO.getFirstName());
        name.setMiddle(contactDTO.getMiddleName() == null ? null : contactDTO.getMiddleName());
        name.setLast(contactDTO.getLastName() == null ? null : contactDTO.getLastName());

//        phone.setNumber(phoneDTO.getPhoneNumber() == null ? null : phoneDTO.getPhoneNumber());
//        phone.setType(phoneDTO.getPhoneType() == null ? null : phoneDTO.getPhoneType());

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
