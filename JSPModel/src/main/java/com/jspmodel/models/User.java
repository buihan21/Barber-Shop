package com.jspmodel.models;

public class User {
    private int id, role_id;
    private String user_name, pass_word, name, phone_number;

    public User() {
        super();
    }

    public User(int id, int role_id, String user_name, String pass_word, String name, String phone_number) {
        super();
        this.id = id;
        this.role_id = role_id;
        this.user_name = user_name;
        this.pass_word = pass_word;
        this.name = name;
        this.phone_number = phone_number;
    }

    public int getId() {
        return id;
    }

    public int getRole_id() {
        return role_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getPass_word() {
        return pass_word;
    }

    public String getName() {
        return name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setPass_word(String pass_word) {
        this.pass_word = pass_word;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
