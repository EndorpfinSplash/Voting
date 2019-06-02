package com.itacademy.repository.impl.hibernateImpl;

import com.itacademy.domain.hibernateDomain.VariantAnswer;
import com.itacademy.repository.VariantAnswerHibernateDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("variantAnswerDaoHibernateImpl")
public class VariantAnswerDaoHibernateImpl implements VariantAnswerHibernateDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    @Override
    public List<VariantAnswer> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT v FROM VariantAnswer v", VariantAnswer.class).list();
        }
    }

    @Override
    public VariantAnswer findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT v FROM VariantAnswer v where v.pollId = :id", VariantAnswer.class).uniqueResult();
        }
    }

    @Override
    public void delete(Long id) {
        VariantAnswer variantAnswer = findById(id);
        try (Session session = sessionFactory.openSession()) {
            session.delete(variantAnswer);
        }
    }

    @Override
    public VariantAnswer save(VariantAnswer entity) {

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
    public VariantAnswer update(VariantAnswer entity) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback(); }
            e.printStackTrace();
        }
        return entity;
    }

}
