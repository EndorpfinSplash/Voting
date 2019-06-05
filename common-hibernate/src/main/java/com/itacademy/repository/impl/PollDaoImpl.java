package com.itacademy.repository.impl;

import com.itacademy.domain.Poll;
import com.itacademy.repository.PollDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("pollDaoImpl")
public class PollDaoImpl extends AbstractRepository implements PollDao {


    @Override
    public List<Poll> findAll() {
        try (Session session = super.sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Poll p", Poll.class).list();
        }
    }

    @Override
    public Poll findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT p FROM Poll p where p.pollId = :idPoll", Poll.class)
                    .setParameter("idPoll", id)
                    .uniqueResult();
        }
    }


    @Override
    public void delete(Long id) {
        Poll poll = findById(id);
        doDeleteTransaction(poll);
    }

    @Override
    public Poll save(Poll entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long entityID = (Long) session.save(entity);
            transaction.commit();
            return session.find(Poll.class, entityID);
        }
    }

    @Override
    public Poll update(Poll entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(Poll.class, entity.getPollId());
        }
    }

}
