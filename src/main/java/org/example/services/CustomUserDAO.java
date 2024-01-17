package org.example.services;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.example.datamodel.CustomUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomUserDAO {
    private final SessionFactory sessionFactory = CustomUserSessionFactory.getUserSessionFactory();
    List<CustomUser> customers = new ArrayList<>();

    public void saveUser(CustomUser user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(user);
        transaction.commit();
        session.close();
    }

    public void removeStudent(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomUser user = session.get(CustomUser.class, id);
            if (user != null) {
                session.remove(user);
                System.out.println("User " + id + " is deleted");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void findUserById(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            CustomUser user = session.get(CustomUser.class, id);
            System.out.print(user.getName() + " ");
            System.out.println(user.getLastName());
            System.out.println(user.getEmail());
            System.out.println(user.getAge());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public CustomUser findUserByEmail(String email) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<CustomUser> userQuery = cb.createQuery(CustomUser.class);
        Root<CustomUser> root = userQuery.from(CustomUser.class);
        userQuery.select(root).where(cb.equal(root.get("email"), email));
        CustomUser result = session.createQuery(userQuery).getSingleResultOrNull();
        return result;
    }

    public List<CustomUser> findAllUsers() {
        Session session = sessionFactory.openSession();
        try {
            List<CustomUser> users = session.createNativeQuery("Select * from user_table", CustomUser.class).getResultList();
            for (int i = 0; i < users.size(); i++) {
                System.out.println(users.get(i));
            }
            return users;
        } catch (Exception e) {
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public String toString() {
        return "CustomUserDAO{" +
                "sessionFactory=" + sessionFactory +
                ", customers=" + customers +
                '}';
    }
}
