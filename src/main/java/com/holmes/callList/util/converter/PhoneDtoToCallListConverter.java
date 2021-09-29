package com.holmes.callList.util.converter;

import com.holmes.callList.dto.PhoneDTO;
import com.holmes.callList.model.CallList;
import com.holmes.callList.model.Name;


public class PhoneDtoToCallListConverter {

    public CallList converter(PhoneDTO phoneDTO){
        CallList callList = new CallList();
        Name name = new Name();

        name.setFirst(phoneDTO.getContact_id().getFirstName() == null ? null : phoneDTO.getContact_id().getFirstName());
        name.setMiddle(phoneDTO.getContact_id().getMiddleName() == null ? null : phoneDTO.getContact_id().getMiddleName());
        name.setLast(phoneDTO.getContact_id().getLastName() == null ? null : phoneDTO.getContact_id().getLastName());

        callList.setName(name);

        callList.setPhoneNumber(phoneDTO.getPhoneNumber() == null ? null : phoneDTO.getPhoneNumber());

        return callList;
    }
}
