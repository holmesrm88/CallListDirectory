package com.holmes.callList.repository;

import com.holmes.callList.dto.ContactDTO;
import com.holmes.callList.dto.PhoneDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhoneRepository extends JpaRepository<PhoneDTO, Long> {

    @Query(value = "SELECT * FROM PHONE p JOIN CONTACT c on c.contact_id = p.contact_id WHERE p.PHONE_TYPE = 'home' ORDER BY c.LAST_NAME", nativeQuery = true)
    List<ContactDTO> getCallList();
}
