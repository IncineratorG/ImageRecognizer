package com.videoprocessor.classes;

/**
 * Created by Alexander on 13.04.2018.
 */
public class Account {
    private String name;
    private String password;


    public Account(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Account() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty() {
        return name.isEmpty() && password.isEmpty();
    }

    public boolean isIncomplete() {
        return name.isEmpty() || password.isEmpty();
    }


    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
