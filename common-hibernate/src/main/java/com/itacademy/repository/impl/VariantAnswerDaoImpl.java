package com.itacademy.repository.impl;

import com.itacademy.domain.Poll;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.VariantAnswerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
@Qualifier("variantAnswerDaoImpl")
public class VariantAnswerDaoImpl implements VariantAnswerDao {

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
            return session.find(VariantAnswer.class, id);
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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long entityID = (Long) session.save(entity);
            transaction.commit();
            return session.find(VariantAnswer.class, entityID);
        }
    }

    @Override
    public VariantAnswer update(VariantAnswer entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(VariantAnswer.class, entity.getAnswerId());
        }
    }

    @Override
    public List<VariantAnswer> findVariantAnswersForPull(Long poll_id) {

        try (Session session = sessionFactory.openSession()) {

            TypedQuery<Poll> query = session
                    .createQuery("SELECT p FROM Poll p where p.pollId = :pollId", Poll.class);
            Poll poll = query
                    .setParameter("pollId", poll_id)
                    .getSingleResult();
            return new ArrayList<>(poll.getVariantAnswers());
        }
    }
}
