package ru.productstar.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.productstar.dao.HibernateContactDao;
import ru.productstar.dao.entity.Contact;
import ru.productstar.service.ContactReader;
import ru.productstar.service.ContactService;

@Configuration
public class ContactConfiguration {

    @Bean
    public SessionFactory sessionFactory() {
        var configuration = new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(Contact.class)
                .configure();
        return configuration.buildSessionFactory();
    }

    @Bean
    public HibernateContactDao contactDao() {
        return new HibernateContactDao(sessionFactory());
    }

    @Bean
    public ContactReader contactParser() {
        return new ContactReader();
    }

    @Bean
    public ContactService contactService() {
        return new ContactService(
                contactDao(),
                contactParser()
        );
    }
}
