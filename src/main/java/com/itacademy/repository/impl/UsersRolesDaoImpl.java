package com.itacademy.repository.impl;

import com.itacademy.domain.Role;
import com.itacademy.repository.UsersRolesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UsersRolesDaoImpl implements UsersRolesDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    final String ROLE_ID = "role_id";
    final String ROLE_NAME = "role_name";
    final String ROLE_USER_ID = "role_user_id";

    public Role getRoleFromRow(ResultSet rowSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(rowSet.getLong(ROLE_ID));
        role.setRoleName(rowSet.getString(ROLE_NAME));
        role.setUserId(rowSet.getLong(ROLE_USER_ID));
        return role;
    }

    @Override
    public List<Role> getRolesByUserId(Long userId) {
        final String findAllUserRoles = "select * from users_roles where user_id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        return namedParameterJdbcTemplate.query(findAllUserRoles, params, this::getRoleFromRow);
    }

    @Override
    public List<Role> findAll() {
        String getAllRoles = "select * from users_roles";
        return namedParameterJdbcTemplate.query(getAllRoles, this::getRoleFromRow);
    }

    @Override
    public Role findById(Long userId) {
        String getAllRoles = "select * from users_roles where role_user_id = :userId";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("userId", userId);
        return namedParameterJdbcTemplate.queryForObject(getAllRoles, params, this::getRoleFromRow);
    }

    @Override
    public void delete(Long roleId) {

        String deleteRole = "delete from users_roles where role_id = :roleId";

        SqlParameterSource params = new MapSqlParameterSource();
        ((MapSqlParameterSource) params).addValue("roleId", roleId);
        namedParameterJdbcTemplate.update(deleteRole, params);
    }

    @Override
    public Role save(Role entity) {

        String saveRoleQuery = "insert into users_roles (role_name, role_user_id) " +
                "values (:roleName,:role_user_id)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleName", entity.getRoleName());
        params.addValue("role_user_id", entity.getUserId());

        KeyHolder generatedKey = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(saveRoleQuery, params, generatedKey);
        Long createdRole = generatedKey.getKey().longValue();
        return findById(createdRole);
    }

    @Override
    public Role update(Role entity) {
        return null;
    }
}
