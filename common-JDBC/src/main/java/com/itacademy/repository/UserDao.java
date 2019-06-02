package com.itacademy.repository;

import com.itacademy.domain.Role;
import com.itacademy.domain.User;
import com.itacademy.domain.VariantAnswer;

import java.util.List;

public interface UserDao extends GenericDao<User, Long> {

     User findByLogin(String login);

     /* ~~~ Roles DAO ~~~*/

     Role setUserRole(Long userId, Long roleId);

     List<Role> getUserRoles(Long userId);

     void deleteUserRole(Long userId, Long roleId);

     /* ~~~ Answers DAO ~~~*/

     void setUserVariantAnswer(Long userId, Long answerId);

     List<VariantAnswer> getUserVariantsAnswer(Long id);

     void deleteVariantAnswer(Long userId, Long answerId);

     // update = delete + set

}
