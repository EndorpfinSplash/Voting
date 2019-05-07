package com.itacademy.repository;

import com.itacademy.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RoleDao extends GenericDao<Role, Long> {

    Role getRoleFromRow(ResultSet resultSet, int i)  throws SQLException;

    List<Role> getRolesByUserId(Long userId);
}
