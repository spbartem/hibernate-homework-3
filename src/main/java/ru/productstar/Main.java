package ru.productstar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.productstar.dao.HibernateContactDao;

public class Main {
    public static void main(String[] args) {
        var applicationContext = new AnnotationConfigApplicationContext(ContactConfiguration.class);

        var contactDao = applicationContext.getBean(HibernateContactDao.class);
        //var contactService = applicationContext.getBean(ContactService.class);

        System.out.println(contactDao.getAllContacts());

        //contactService.saveContacts(Path.of("src/main/resources/contacts.csv"));

        //contactDao.updatePhoneNumber(1, "+79119110011");
        //contactDao.updateEmail(1, "petr_petrov@gmail.com");
    }
}
