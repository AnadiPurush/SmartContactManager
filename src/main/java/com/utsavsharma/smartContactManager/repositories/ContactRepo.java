package com.utsavsharma.smartContactManager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utsavsharma.smartContactManager.Entity.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, String> {

    // List<Contact> getContactByUser(String user);
}
