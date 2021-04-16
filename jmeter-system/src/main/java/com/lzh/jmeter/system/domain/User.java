package com.lzh.jmeter.system.domain;

import com.lzh.jmeter.commons.core.web.domain.BaseEntity;

public class User extends BaseEntity {

    private Integer userId;

    private String userName;

    private String email;

    private String password;

    private String salt;

    private Integer state;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCredentialsSalt() {
        return this.userName + this.salt;
    }

}
