package com.holmes.callList.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
//@Entity
//@Table(name = "name")
//@Data
//@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Name {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
    private long id;

   // @Column(name = "first")
    private String first;

   // @Column(name = "middle")
    private String middle;

    //@Column(name = "last")
    private String last;

}
