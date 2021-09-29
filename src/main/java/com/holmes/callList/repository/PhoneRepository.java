package com.holmes.callList.repository;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneDTO, Long> {

    @Query(value = "SELECT\n" +
            "     p.phone_id,\n" +
            "     c.contact_id,\n" +
            "     p.contact_id,\n" +
            "     c.first_name,\n" +
            "     c.middle_name,\n" +
            "     c.last_name,\n" +
            "     p.phone_type,\n" +
            "     p.phone_number\n" +
            "FROM Phone p\n" +
            "JOIN Contact c on c.contact_id = p.contact_id\n" +
            "WHERE p.phone_type = 'home'\n" +
            "ORDER BY c.last_name", nativeQuery = true)
    List<PhoneDTO> getCallList();
}
