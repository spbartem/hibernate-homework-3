package ru.productstar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.productstar.config.ContactConfiguration;
import ru.productstar.dao.HibernateContactDao;
import ru.productstar.service.ContactService;
import ru.productstar.dao.entity.Contact;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ContactConfiguration.class)
public record ContactDaoTests(@Autowired HibernateContactDao contactDao,
                              @Autowired ContactService contactService) {

    private static final Contact JACKIE = new Contact(
            1L, "Jackie", "Chan", "jchan@gmail.com", "1234567890"
    );

    private static final Contact PETR = new Contact(
            2L,"Petr", "Petrov", "ppetrov@gmail.com", "+70987654321"
    );

    private static final Contact JOHN = new Contact(
            3L, "John", "Smith", "jsmith@gmail.com", "+11234567890"
    );

    private static final Contact SEMION = new Contact(
            4L,"Semion", "Cowl", "ssemenov@gmail.com", "+71234567890"
    );

    private static final List<Contact> PERSISTED_CONTACTS = List.of(JACKIE, PETR, JOHN, SEMION);

    @Test
    void saveContacts() {
        contactService.saveContacts(Path.of("src/main/resources/contacts.csv"));
        var contacts = contactDao.getAllContacts();
        assertThat(contacts).containsAll(PERSISTED_CONTACTS);
    }

    @Test
    void addContact() {
        var contact = new Contact("Jackie", "Chan", "jchan@gmail.com", "1234567890");
        var contactId = contactDao.addContact(contact);
        contact.setId(contactId);

        var contactInDb = contactDao.getContact(contactId);

        assertThat(contactInDb).isEqualTo(contact);
    }

    @Test
    void getContact() {
        contactService.saveContacts(Path.of("src/main/resources/contacts.csv"));
        var contact = contactDao.getContact(PETR.getId());
        assertThat(contact).isEqualTo(PETR);
    }

    @Test
    void getAllContacts() {
        contactService.saveContacts(Path.of("src/main/resources/contacts.csv"));
        var contacts = contactDao.getAllContacts();

        assertThat(contacts).containsAll(PERSISTED_CONTACTS);
    }

    @Test
    void updatePhoneNumber() {
        var contact = new Contact("Jekyll", "Hide", "jhide@gmail.com", "");
        var contactId = contactDao.addContact(contact);

        var newPhone = "777-77-77";
        contactDao.updatePhoneNumber(contactId, newPhone);

        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getPhone()).isEqualTo(newPhone);
    }

    @Test
    void updateEmail() {
        var contact = new Contact("Captain", "America", "", "");
        var contactId = contactDao.addContact(contact);

        var newEmail = "cap@gmail.com";
        contactDao.updateEmail(contactId, newEmail);

        var updatedContact = contactDao.getContact(contactId);
        assertThat(updatedContact.getEmail()).isEqualTo(newEmail);
    }

    @Test
    void deleteContact() {
        var contact = new Contact("To be", "Deleted", "", "");
        var contactId = contactDao.addContact(contact);

        contactDao.deleteContact(contactId);

        var deletedContact = contactDao.getContact(contactId);
        assertThat(deletedContact).isEqualTo(null);
    }
}
