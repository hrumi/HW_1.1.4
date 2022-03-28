package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import java.util.Properties;


public class Util {

    private static SessionFactory sessionFactory;

    public SessionFactory getSession() {
        Properties pr = new Properties();
        pr.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/new_schema_2");
        pr.setProperty("hibernate.connection.username", "hrumi");
        pr.setProperty("hibernate.connection.password", "Lapoipopopeh");
        pr.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        pr.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        pr.setProperty("show_sql", "true");
        pr.setProperty("format_sql", "true");

        if (sessionFactory == null) {
                Configuration configuration = new Configuration().addProperties(pr);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(pr).build();
                sessionFactory = configuration.buildSessionFactory(sr);
        }
        return sessionFactory;
    }

    public void closeSessionFactory (SessionFactory sf) {
            sf.close();
    }

}
