package com.si2001.crudexample.DAO;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import com.si2001.crudexample.util.JpaUtil;
import com.si2001.crudexample.model.User;

public class UserDAO {

    private static final EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();

    public void insertUser(User user){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.flush(); // force the data to be persisted in the database immediately
        em.getTransaction().commit();
        em.close();
    }

    public User selectUserById(int id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        User user = em.find(User.class, id);
        em.getTransaction().commit();
        em.close();

        return (user != null) ? user : null;
    }

    public List<User> selectAllUsers() {
        List<User> listaResult = new ArrayList<User>();
        EntityManager em = emf.createEntityManager();

        TypedQuery<User> query = em.createQuery("SELECT u from User u", User.class);
        listaResult = query.getResultList();
        em.close();

        return listaResult.isEmpty() ? null : listaResult;
    }

    public void deleteUser(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        User user = em.find(User.class, id);
        em.remove(user);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }

    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.merge(user);
        em.flush();
        em.getTransaction().commit();
        em.close();
    }
}
