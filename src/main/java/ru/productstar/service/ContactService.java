package ru.productstar.service;

import org.springframework.stereotype.Service;
import ru.productstar.dao.HibernateContactDao;
import ru.productstar.dao.entity.Contact;

import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContactService {

    private final Logger logger = LoggerFactory.getLogger(ContactService.class);

    private final HibernateContactDao contactDao;
    private final ContactReader contactReader;

    public ContactService(HibernateContactDao contactDao, ContactReader contactReader) {
        this.contactDao = contactDao;
        this.contactReader = contactReader;
    }

    public void saveContacts(Path filePath) {
        logger.info("Importing contacts from {}", filePath);
        var contacts = contactReader.readFromFile(filePath);
        logger.info("Contacts has been imported successfully, count: {}", contacts.size());
        contactDao.saveAll(contacts);
    }

    public List<Contact> getContacts() {
        return contactDao.getAllContacts();
    }
}
