package com.itacademy.repository.impl.hibernateImpl;

import com.itacademy.domain.Role;
import com.itacademy.domain.User;
import com.itacademy.domain.VariantAnswer;
import com.itacademy.repository.RoleDao;
import com.itacademy.repository.UserDao;
import com.itacademy.repository.VariantAnswerDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
@Qualifier("UserDaoHibernateImpl")
public class UserDaoHibernateImpl implements UserDao {


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
            return session.createQuery("select u from User u where u.userId =:id", User.class).uniqueResult();
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
    public User update(User entity) {

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

    @Autowired
    @Qualifier("roleDaoHibernateImpl")
    RoleDao roleDao;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Role setUserRole(Long userId, Long roleId) {
        final String saveUserQuery = "INSERT INTO users_roles(user_id, role_id) " +
                "VALUES (:userId, :roleId)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        params.addValue("roleId", roleId);
        namedParameterJdbcTemplate.update(saveUserQuery, params, keyHolder);

        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        return roleDao.findById(createdRoleId);
    }

    @Override
    public List<Role> getUserRoles(Long userId) {
        final String getUsersRolesQuery = "SELECT * from voting.users_roles ur left join voting.roles r on r.role_id = ur.role_id" +
                " where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);

        return namedParameterJdbcTemplate.query(getUsersRolesQuery, params, roleDao::getRoleFromRow);
    }

    @Override
    public void deleteUserRole(Long userId, Long roleId) {
        final String deleteUserRole = "DELETE from voting.users_roles where user_id=:userId and role_id = :roleId";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("roleId", roleId);
        namedParameterJdbcTemplate.update(deleteUserRole, param);
    }


    @Autowired
    @Qualifier("variantAnswerDaoHibernateImpl")
    VariantAnswerDao variantAnswerDao;

    @Override
    public void setUserVariantAnswer(Long userId, Long answerId) {

        final String saveUserVariantAnswer = "INSERT INTO voting.users_answers(answer_date, user_id, answer_id) " +
                "VALUES (:answerDate, :userId, :variantAnswerId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("answerDate", LocalDateTime.now());
        params.addValue("userId", userId);
        params.addValue("variantAnswerId", answerId);
        namedParameterJdbcTemplate.update(saveUserVariantAnswer, params);
    }

    @Override
    public List<VariantAnswer> getUserVariantsAnswer(Long id) {
        final String getUserVariantAnswer = "select * from voting.users_answers " +
                "join voting.variant_answers on users_answers.answer_id = variant_answers.answer_id" +
                " where user_id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);
        return namedParameterJdbcTemplate.query(getUserVariantAnswer, params, variantAnswerDao::getAnswerFromRow);
    }

    @Override
    public void deleteVariantAnswer(Long userId, Long answerId) {
        final String deleteVariantAnswer = "DELETE from voting.users_answers where user_id = :userId and answer_id = :answerId";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        param.addValue("answerId", answerId);
        namedParameterJdbcTemplate.update(deleteVariantAnswer, param);

    }


    @Override
    public User findByLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("select u from User u where u.login =:login", User.class).uniqueResult();
        }
    }
}
