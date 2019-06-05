package com.itacademy.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

abstract class AbstractRepository {

     @Autowired
     @Qualifier("sessionFactory")
     protected SessionFactory sessionFactory;

     <T> void doDeleteTransaction(T entity) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(entity);
            transaction.commit();
        }
    }
}
