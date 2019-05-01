package com.itacademy.repository.impl;

import com.itacademy.domain.User;
import com.itacademy.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    @Override
    public List<User> findAll() {
        final String getAllUserQuery = "select * from user";
        return namedParameterJdbcTemplate.query(getAllUserQuery, this::getRowToUser);
    }

    @Override
    public User findById(Long id) {
        final String getUserByIDQuery = "select * from user where user_id = ?";
        return jdbcTemplate.queryForObject(getUserByIDQuery, new Object[]{id}, this::getRowToUser);
    }

    @Override
    public void delete(Long id) {
        final String deleteUserQuery = "select * from user where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", id);

        namedParameterJdbcTemplate.update(deleteUserQuery, params);
    }

    @Override
    public User save(User entity) {

        final String saveUserQuery = "INSERT INTO user(user_name, user_surname, registration_date,login,password) " +
                "VALUES (:userName, :userSurname, :registrationDate, :login, :passwd)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userName", entity.getUserName());
        params.addValue("userSurname", entity.getUserSurname());
        params.addValue("registrationDate", entity.getRegistrationDate());
        params.addValue("logih", entity.getLogin());
        params.addValue("passwd", entity.getPassword());
        namedParameterJdbcTemplate.update(saveUserQuery, params, keyHolder);

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User entity) {

        final String updateUserQuery =
                "update users set user_name= :userName , user_surname = :userSurname, registration_date = :registrationDate, login = :login, password = :password" +
                        " where user_id = :userId";

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("user_id", entity.getUserId());
        params.addValue("userName", entity.getUserName());
        params.addValue("userSurname", entity.getUserSurname());
        params.addValue("registrationDate", entity.getRegistrationDate());
        params.addValue("login", entity.getLogin());
        params.addValue("pass", entity.getPassword());

        namedParameterJdbcTemplate.update(updateUserQuery, params);
        return findById(entity.getUserId());
    }


}
