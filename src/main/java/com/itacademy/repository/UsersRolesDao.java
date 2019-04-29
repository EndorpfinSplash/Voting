package com.itacademy.repository;

import com.itacademy.domain.Role;

import java.util.List;

public interface UsersRolesDao extends GenericDao<Role, Long> {
    List<Role> getRolesByUserId(Long userId);
}
