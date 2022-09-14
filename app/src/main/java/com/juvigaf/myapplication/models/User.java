package com.juvigaf.myapplication.models;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String username;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Integer role;
    private List<Order>orders = new ArrayList<>();
    private int profile;
    private Uri uri;
    private Double rating;
    private Integer price;

    public User(String username, String name, String email, String password, String phone, Integer role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.profile = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
