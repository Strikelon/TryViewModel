package ru.geekbrains.android.arch.mvvm.model.entities;

import androidx.annotation.NonNull;

public class User {

    private final int id;
    private final String name;
    private final String username;
    private final String email;
    private final String phone;
    private final String city;

    public User(int id, String name, String username, String email, String phone, String city) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
