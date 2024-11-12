package com.utsavsharma.smartContactManager.services.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.utsavsharma.smartContactManager.Entity.Contact;
import com.utsavsharma.smartContactManager.repositories.ContactRepo;
import com.utsavsharma.smartContactManager.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepo contactRepo;

    public ContactServiceImpl(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    // @Override
    // public Contact saveContact(Contact contact) {
    // String ContactId = UUID.randomUUID().toString();
    // contact.setId(ContactId);
    // return contactRepo.save(contact);
    // }

    // @Override
    // public Contact updateContact(Contact contact) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public List<Contact> getAllContacts() {
    // return contactRepo.findAll();

    // }

    // @Override
    // public Contact getContactById(String id) {
    // return contactRepo.findById(id).orElseThrow(() -> new
    // ResourceNotFoundException("Contact not found"));
    // }

    // @Override
    // public Page<Contact> searchByName(String nameKeyword, int size, int page,
    // String sortBy, String order, User user) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public Page<Contact> searchByEmail(String emailKeyword, int size, int page,
    // String sortBy, String order,
    // User user) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size,
    // int page, String sortBy, String order,
    // User user) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public List<Contact> getByUserId(String userId) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public Page<Contact> getByUser(User user, int page, int size, String
    // sortField, String sortDirection) {
    // throw new UnsupportedOperationException("Not supported yet.");
    // }

    // @Override
    // public List<Contact> getContactByUser(String user) {
    // return contactRepo.getContactByUser(user);
    // }

    // @Override
    // public List<Contact> findAll() {
    // return contactRepo.findAll();
    // }

    @Override
    public ArrayList<Contact> findAll() {
        return (ArrayList<Contact>) contactRepo.findAll();
    }

}
