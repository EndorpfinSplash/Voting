package com.itacademy.repository;

import com.itacademy.domain.Role;
import com.itacademy.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

     Role setUserRole(User user, Role role);

     List<Role> getUserRoles(User user);

     Role updateUserRoles(User user, Role role);

     void deleteUserRole (User user, Role role);

}
