package ru.productstar.dao;

import ru.productstar.dao.entity.Contact;

import java.util.*;

public interface ContactDao {

    long addContact(Contact contact);
    void saveAll(Collection<Contact> contacts);

    Contact getContact(long contactId);
    List<Contact> getAllContacts();

    void deleteContact(long contactId);

    void updatePhoneNumber(long contactId, String phoneNumber);
    void updateEmail(long contactId, String email);

}
