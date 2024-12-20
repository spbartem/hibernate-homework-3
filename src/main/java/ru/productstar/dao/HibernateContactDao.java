package ru.productstar.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.productstar.dao.entity.Contact;
import ru.productstar.service.ContactService;

import java.util.Collection;
import java.util.List;

public class HibernateContactDao {

    private final Logger logger = LoggerFactory.getLogger(ContactService.class);
    private final SessionFactory sessionFactory;

    public HibernateContactDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Contact> getAllContacts() {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM Contact c", Contact.class).getResultList();
        }
    }

    public Contact getContact(long contactId) {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            return session.get(Contact.class, contactId);
        }
    }

    public long addContact(Contact contact) {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(contact);
            Long contactId = contact.getId();
            transaction.commit();
            logger.info("Contact {} has been add added", contact);
            return contactId;
        }
    }

    public void saveAll(Collection<Contact> contacts) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();

            for (var contact : contacts) {
                session.persist(contact);
            }
            transaction.commit();
        }
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            Contact contact = getContact(contactId);
            if (contact != null) {
                String oldPhoneNumber = contact.getPhone();
                contact.setPhone(phoneNumber);
                session.merge(contact);
                transaction.commit();
                logger.info("The contact with ID {} has an updated phone number {} -> {}", contact.getId(), oldPhoneNumber, contact.getPhone());
            }
        }
    }

    public void updateEmail(long contactId, String email) {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContact(contactId);
            if (contact != null) {
                String oldEmail = contact.getPhone();
                contact.setEmail(email);
                session.merge(contact);
                logger.info("The contact with ID {} has an updated email {} -> {}", contact.getId(), oldEmail, contact.getEmail());
            }
            transaction.commit();
        }
    }

    public void deleteContact(long contactId) {
        // TODO Implement me!

        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            var contact = getContact(contactId);
            if (contact != null) {
                session.remove(contact);
                logger.info("Contact with ID {} has been deleted", contact.getId());
            }
            transaction.commit();
        }
    }
}
