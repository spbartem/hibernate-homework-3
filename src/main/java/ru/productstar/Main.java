package ru.productstar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.productstar.config.ContactConfiguration;
import ru.productstar.dao.HibernateContactDao;
import ru.productstar.dao.entity.Contact;
import ru.productstar.service.ContactService;

import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ContactConfiguration.class);

        var contactDao = applicationContext.getBean(HibernateContactDao.class);
        var contactService = applicationContext.getBean(ContactService.class);

        System.out.println("===Adding records to DB from csv file===");
        Path of = Path.of("src/main/resources/contacts.csv");
        contactService.saveContacts(of);
        System.out.println(contactDao.getAllContacts());

        System.out.println("\n===Adding one contact to DB===");
        long contactId = contactDao.addContact(new Contact("Steven", "King",
                "kingofhorror@gmail.com", "+155512345666"));
        System.out.println("Contact added with ID " +  contactId);

        System.out.println("\n===Updating phone number and email===");
        contactDao.updatePhoneNumber(4, "+79119110011");
        contactDao.updateEmail(1, "petr_petrov@yahoo.com");
        System.out.println(contactDao.getAllContacts());

        System.out.println("\n===Deleting contact with ID #2===");
        contactDao.deleteContact(2);
        System.out.println(contactDao.getAllContacts());
    }
}
