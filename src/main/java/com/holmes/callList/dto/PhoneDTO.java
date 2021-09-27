package com.holmes.callList.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "phone")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PhoneDTO {

    @Id
    @Column(name = "id")
    private long id;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "phone_number")
    private String phoneNumber;
}
