package com.itacademy.repository.impl;

import com.itacademy.domain.Poll;
import com.itacademy.repository.PollDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("pollDaoImpl")
public class PollDaoImpl implements PollDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<Poll> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Poll p", Poll.class).list();
        }
    }

    @Override
    public Poll findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Poll p where p.pollId = :id", Poll.class).uniqueResult();
        }
    }


    @Override
    public void delete(Long id) {
        Poll poll = findById(id);

        try (Session session = sessionFactory.openSession()) {
            session.delete(poll);
        }

    }

    @Override
    public Poll save(Poll entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public Poll update(Poll entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return entity;
    }

}
