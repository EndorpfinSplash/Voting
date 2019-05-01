package com.itacademy.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Role {
    private Long roleId;
    private String roleName;

    public Role() {
    }

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
    }
}
