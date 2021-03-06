package com.itacademy.controller.requests;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.sql.Timestamp;
import java.util.Objects;

public class UserCreateRequest {

    private String userName;
    private String userSurname;
    private Timestamp registrationDate;
    private String login;
    private String password;

    public UserCreateRequest() {
    }

    public UserCreateRequest(String userName, String userSurname, Timestamp registrationDate, String login, String password) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.registrationDate = registrationDate;
        this.login = login;
        this.password = password;
    }


    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserCreateRequest that = (UserCreateRequest) o;
        return Objects.equals(userName, that.userName) &&
                Objects.equals(userSurname, that.userSurname) &&
                Objects.equals(registrationDate, that.registrationDate) &&
                Objects.equals(login, that.login) &&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, userSurname, registrationDate, login, password);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
