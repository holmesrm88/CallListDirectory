package com.holmes.callList.repository;

import com.holmes.callList.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CallListRepository extends JpaRepository<Contact, Long> {


    @Query(value = "SELECT * FROM Contact ORDER BY name", nativeQuery = true)
    List<Contact> getCallList();
}
