package com.itacademy.repository;

import com.itacademy.domain.Role;
import com.itacademy.domain.User;
import com.itacademy.domain.VariantAnswer;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

     User findByLogin(String login);

     /* ~~~ Roles DAO ~~~*/

     Role setUserRole(User user, Role role);

     List<Role> getUserRoles(User user);

     void deleteUserRole (User user, Role role);

     /* ~~~ Answers DAO ~~~*/

     void setUserVariantAnswer(User user, VariantAnswer variantAnswer);

     List<VariantAnswer> getUserVariantsAnswer(User user);

     void deleteVariantAnswer (User user, VariantAnswer variantAnswer);

     // update = delete + set

}
