package com.jpa.librarymanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    private String userName;

    private int age;

    private boolean isActive;

    private String email;

    private String mobileNumber;

    @OneToMany(	mappedBy="user",
            cascade= {CascadeType.ALL},
            fetch=FetchType.LAZY)
    private List<Rental> reservedBooks;

    public User(){

    }
    public User(String userName, int age, boolean isActive, String email, String mobileNumber) {
        this.userName = userName;
        this.age = age;
        this.isActive = isActive;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                ", isActive=" + isActive +
                ", email='" + email + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }

}

