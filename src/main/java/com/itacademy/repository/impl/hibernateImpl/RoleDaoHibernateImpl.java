package com.itacademy.repository.impl.hibernateImpl;

import com.itacademy.domain.hibernateDomain.Role;
import com.itacademy.repository.RoleHibernateDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("roleDaoHibernateImpl")
public class RoleDaoHibernateImpl implements RoleHibernateDao {

    @Autowired
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;


    @Override
    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Role r", Role.class).list();
        }
    }

    @Override
    public Role findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Role r where r.roleId = :id", Role.class).uniqueResult() ;
        }
    }

    @Override
    public void delete(Long id) {

        Role role = findById(id);
        try (Session session = sessionFactory.openSession()) {
            session.delete(role);
        }
    }

    @Override
    public Role save(Role entity) {

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
    public Role update(Role entity) {
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
