package com.utsavsharma.smartContactManager.services;

import java.util.ArrayList;

import com.utsavsharma.smartContactManager.Entity.Contact;

public interface ContactService {

    // Contact saveContact(Contact contact);

    // Contact updateContact(Contact contact);

    // List<Contact> getAllContacts();

    // Contact getContactById(String id);

    // Page<Contact> searchByName(String nameKeyword, int size, int page, String
    // sortBy, String order, User user);

    // Page<Contact> searchByEmail(String emailKeyword, int size, int page, String
    // sortBy, String order, User user);

    // Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int
    // page, String sortBy, String order,
    // User user);

    // List<Contact> getByUserId(String userId);

    // Page<Contact> getByUser(User user, int page, int size, String sortField,
    // String sortDirection);

    // List<Contact> getContactByUser(String user);
    ArrayList<Contact> findAll();

}
