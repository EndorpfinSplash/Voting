package com.itacademy.repository;

import com.itacademy.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoleDao extends GenericDao<com.itacademy.domain.Role, Long> {

    Role getRoleFromRow(ResultSet resultSet, int i)  throws SQLException;

}
