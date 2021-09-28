package com.holmes.callList.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "contact")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Getter
@Setter
public class ContactDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private long contact_id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip", nullable = false)
    private String zip;

    @Column(name = "phone", nullable = false)
    @JsonManagedReference
    @OneToMany(mappedBy = "contact_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhoneDTO> phone = new ArrayList<>();

    @Column(name = "email", nullable = false)
    private String email;

    public void addToPhoneDto(PhoneDTO dto){
        dto.setContact_id(this);
        this.phone.add(dto);
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
                "id=" + contact_id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                '}';
    }
}
