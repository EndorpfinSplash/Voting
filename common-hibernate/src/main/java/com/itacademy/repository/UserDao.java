package com.itacademy.repository;

import com.itacademy.domain.User;

public interface UserDao extends GenericDao<User, Long> {

    User findByLogin(String login);

}
