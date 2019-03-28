package com.itacademy.repository.impl;

import com.itacademy.domain.User;
import com.itacademy.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    JdbcTemplate jdbcTemplate;
    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "user_name";
    public static final String USER_SURNAME = "user_surname";
    public static final String REGISTRATION_DATE = "registration_date";

    private User getRowToUser(ResultSet rowSet, int i) throws SQLException {
        User user = new User();
        user.setUserId(rowSet.getLong(USER_ID));
        user.setUserName(rowSet.getString(USER_NAME));
        user.setUserSurname(rowSet.getString(USER_SURNAME));
        user.setRegistrationDate(rowSet.getTimestamp(REGISTRATION_DATE));
        return user;
    }

    public List<User> findAll() {
        final String getAllUserQuery = "select * from user";
         return namedParameterJdbcTemplate.query(getAllUserQuery, this::getRowToUser);
    }

    public User findById(Long id) {
        return null;
    }

    public void delete(Long id) {

    }

    public User save(User entity) {
        return null;
    }

    public User update(User entity) {
        return null;
    }
}
