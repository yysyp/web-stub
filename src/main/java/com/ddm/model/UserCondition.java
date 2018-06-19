package com.ddm.model;

import lombok.*;

/**
 * Created by yunpeng.song on 6/14/2018.
 */
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString
public class UserCondition {

    private String userName;

    private String password;

    private String phone;

    public UserCondition(String userName, String password, String phone) {
        this.userName = userName;
        this.password = password;
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
