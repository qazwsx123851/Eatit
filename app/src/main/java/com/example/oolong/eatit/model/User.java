package com.example.oolong.eatit.model;

/**
 * Created by Oolong on 2018/5/9.
 */

public class User {
    private String Accounts,Password;

    public User() {

    }

    public User(String accounts, String password) {
        Accounts = accounts;
        Password = password;
    }

    public String getAccounts() {
        return Accounts;
    }

    public void setAccounts(String accounts) {
        Accounts = accounts;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
