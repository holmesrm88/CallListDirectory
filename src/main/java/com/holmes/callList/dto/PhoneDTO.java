package com.holmes.callList.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Table(name = "phone")
@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@Entity
@Setter
@Getter
public class PhoneDTO {

    @Id
    @Column(name = "phone_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phone_id;

    @Column(name = "phone_type")
    private String phoneType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", nullable = false)
    private ContactDTO contact_id;

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + phone_id +
                ", phoneType='" + phoneType + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", contactDTO=" + contact_id +
                '}';
    }
}
