package com.holmes.callList.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.holmes.callList.dto.ContactDTO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Phone {

    private String number;
    private String type;

    @JsonIgnore
    private ContactDTO contact;

}
