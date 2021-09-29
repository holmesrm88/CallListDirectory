package com.holmes.callList.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
//@Entity
//@Table(name = "contact")
//@Data
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Contact {


    //private long id;

    private Name name;

    private Address address;

    private List<Phone> phone;

    private String email;

}
