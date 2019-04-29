package com.itacademy.domain;

import java.sql.Timestamp;
import java.util.Objects;

public class User {
    private Long userId;
    private String userName;
    private String userSurname;
    private Timestamp registrationDate;
    private String login;
    private String password;

    public User() {}

    public User(Long userId, String userName, String userSurname) {
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.registrationDate =  new Timestamp(System.currentTimeMillis());
    }


    public User(Long userId, String userName, String userSurname, String login, String password) {
        this(userId,userName,userSurname);
        this.login = login;
        this.password =  password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(userSurname, user.userSurname) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userSurname, registrationDate, login, password);
    }


}
