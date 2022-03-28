package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static SessionFactory sf;

    public UserDaoHibernateImpl() {
        sf = new Util().getSession();
    }


    @Override
    public void createUsersTable() {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user"
                    + "("
                    + " id serial,"
                    + " name varchar(40) NOT NULL,"
                    + " lastname varchar(40) NOT NULL,"
                    + " age numeric(3) NOT NULL,"
                    + " PRIMARY KEY (id)"
                    + ")").addEntity(User.class).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = sf.openSession()) {
            String hql = "from User";
            return session.createQuery(hql, User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>() ;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sf.openSession()) {
            Transaction transaction = session.beginTransaction();
            String hql = "delete from User";
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
