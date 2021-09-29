package com.holmes.callList.util.converter;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.CallList;
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
public class PhoneDtoToCallListConverterTest {

    CallList callList;
    PhoneDTO phoneDTO;
    List<PhoneDTO> phoneDTOS;
    ContactDTO contactDTO;
    Name name;

    @InjectMocks
    PhoneDtoToCallListConverter converter;

    @Before
    public void setup(){

        converter = new PhoneDtoToCallListConverter();

        contactDTO = new ContactDTO();
        contactDTO.setLastName("TestLastName");
        contactDTO.setFirstName("Generic");

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

        callList = new CallList();
        callList.setName(name);
        callList.setPhoneNumber(contactDTO.getPhone().get(0).getPhoneNumber());
    }

    @Test
    public void converterTest(){
        CallList result = converter.converter(phoneDTO);
        Assert.assertEquals(callList.getPhoneNumber(), result.getPhoneNumber());
    }
}
