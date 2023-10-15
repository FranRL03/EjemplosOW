package com.ejemplo.SpringJacksonJsonview.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.LocalDateTime;

import static com.ejemplo.SpringJacksonJsonview.model.CustomerView.*;

public class Customer {

    @JsonView(CustomerList.class)
    private Long id;

    @JsonView(CustomerDetail.class)
    private String name;

    @JsonView({CustomerList.class, CustomerEdit.class})
    private String email;

    @JsonView({CustomerDetail.class, CustomerEdit.class})
    private double salary;

    @JsonIgnore
    private String password;

    @JsonView({CustomerEdit.class})
    private String creditCard;

    @JsonView({CustomerDetail.class})
    private LocalDateTime createdAt;

    @JsonView({CustomerDetail.class})
    private LocalDateTime lastLogin;

    @JsonView({CustomerDetail.class, CustomerEdit.class})
    private Integer year;

    @JsonView({CustomerDetail.class, CustomerEdit.class})
    private boolean married;


    public Customer(Long id) {
    }

    public Customer(Long id, String name, String email, double salary, String password,
                    String creditCard, Integer year, boolean married) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.salary = salary;
        this.password = password;
        this.creditCard = creditCard;
        this.createdAt = LocalDateTime.now();
        this.lastLogin = LocalDateTime.now();
        this.year = year;
        this.married = married;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }


}
