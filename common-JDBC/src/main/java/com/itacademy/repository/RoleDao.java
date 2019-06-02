package com.itacademy.repository;

import com.itacademy.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RoleDao extends GenericDao<Role, Long> {

    default Role getRoleFromRow(ResultSet resultSet, int i) throws SQLException {
        return null;
    }

}
