package com.itacademy.repository.impl;

import com.itacademy.domain.User;
import com.itacademy.repository.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("userDaoImpl")
public class UserDaoImpl implements UserDao {


    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select u from User u", User.class).list();
        }
    }

    @Override
    public User findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select u from User u where u.userId =:id", User.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }



    @Override
    public void delete(Long id) {
        User user = findById(id);
        try (Session session = sessionFactory.openSession()) {
            session.delete(user);
        }
    }

    @Override
    public User save(User entity) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long entityID = (Long) session.save(entity);
            transaction.commit();
            return session.find(User.class, entityID);
        }
    }

    @Override
    public User update(User entity) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(User.class, entity.getUserId());
        }
    }


    @Override
    public User findByLogin(String login) {

        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("select u from User u where u.userName =:login", User.class)
                    .setParameter("login", login)
                    .uniqueResult();
        }
    }
}
