package ru.productstar.dao;

import org.hibernate.SessionFactory;
import ru.productstar.dao.entity.Contact;

import java.util.List;

public class HibernateContactDao {

    private final SessionFactory sessionFactory;

    public HibernateContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Contact> getAllContacts() {
        // TODO Implement me!
        try (var session = sessionFactory.openSession()) {
            return session.createQuery("select c from Contact c").getResultList();
        }
    }

    public Contact getContact(long contactId) {
        // TODO Implement me!
        return null;
    }

    public long addContact(Contact contact) {
        // TODO Implement me!
        return -1;
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        // TODO Implement me!
    }

    public void updateEmail(long contactId, String email) {
        // TODO Implement me!
    }

    public void deleteContact(long contactId) {
        // TODO Implement me!
    }
}
