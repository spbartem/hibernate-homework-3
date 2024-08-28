package ru.productstar;

import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Environment;
import org.hibernate.tool.schema.Action;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.productstar.dao.HibernateContactDao;
import ru.productstar.dao.entity.Contact;

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
}
