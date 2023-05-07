package com.example.CaseStudyApp.Entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @Column(name = "user_email")
    private String email;
    @Column(name = "user_password")
    private String password;

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    private List<Order> userOrders;

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, List<Order> userOrders) {
        this.email = email;
        this.password = password;
        this.userOrders = userOrders;
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

    public List<Order> getUserOrders() {
        return userOrders;
    }

    public void setUserOrders(List<Order> userOrders) {
        this.userOrders = userOrders;
    }
}
