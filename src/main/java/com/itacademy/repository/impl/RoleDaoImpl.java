package com.itacademy.repository.impl;

import com.itacademy.domain.Role;
import com.itacademy.repository.RoleDao;
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
public class RoleDaoImpl implements RoleDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    public Role getRoleFromRow(ResultSet resultSet, int i) throws SQLException {
        Role role = new Role();
        role.setRoleId(resultSet.getLong(ROLE_ID));
        role.setRoleName(resultSet.getString(ROLE_NAME));
        return role;
    }

    @Override
    public List<Role> findAll() {
        final String getAllRoles = "select * from roles";
        return jdbcTemplate.query(getAllRoles, this::getRoleFromRow);
    }

    @Override
    public Role findById(Long id) {
        final String findById = "select * from roles where role_id = ?";
        return jdbcTemplate.queryForObject(findById, new Object[]{id}, this::getRoleFromRow);
    }

    @Override
    public void delete(Long id) {
        String deleteRole = "Delete from roles where role_id=:id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", id);
        namedParameterJdbcTemplate.update(deleteRole, params);
    }

    @Override
    public Role save(Role entity) {

        String insertRole = "insert into roles (role_id, role_name) values(:roleId, :roleName)";
        KeyHolder keyHolder = new GeneratedKeyHolder();


        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", entity.getRoleId());
        params.addValue("roleName", entity.getRoleName());
        namedParameterJdbcTemplate.update(insertRole, params, keyHolder);
        long createdRoleId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdRoleId);
    }

    @Override
    public Role update(Role entity) {
        String insertRole = "update roles set role_name =:roleName where role_id = :roleId)";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("roleId", entity.getRoleId());
        params.addValue("roleName", entity.getRoleName());
        namedParameterJdbcTemplate.update(insertRole, params);

        return findById(entity.getRoleId());
    }
}
