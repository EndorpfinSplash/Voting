package com.itacademy.repository.impl;

import com.itacademy.domain.Role;
import com.itacademy.repository.RoleDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("roleDaoImpl")
public class RoleDaoImpl extends AbstractRepository implements RoleDao {


    @Override
    public List<Role> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT r FROM Role r", Role.class).list();
        }
    }

    @Override
    public Role findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT r FROM Role r where r.roleId = :id", Role.class)
                    .setParameter("id", id)
                    .uniqueResult();
        }
    }

    @Override
    public void delete(Long id) {
        Role role = findById(id);
        doDeleteTransaction(role);
    }

    @Override
    public Role save(Role entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            Long entityID = (Long) session.save(entity);
            transaction.commit();
            return session.find(Role.class, entityID);
        }
    }

    @Override
    public Role update(Role entity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(entity);
            transaction.commit();
            return session.find(Role.class, entity.getRoleId());
        }
    }
}
