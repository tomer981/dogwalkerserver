package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@MappedSuperclass
public abstract class User {
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ABOUT_MYSELF")
    private String aboutMyself;

    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "PHONE")
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;


    //CONSTRUCTOR
    public User() {
    }

    public User(String firstName, String lastName, String userName, String password, String email, String aboutMyself, Date birthDay, String phone, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.aboutMyself = aboutMyself;
        this.birthDay = birthDay;
        this.phone = phone;
        this.gender = gender;
    }

    // getter
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUserName() {
        return userName;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getAboutMyself() {
        return aboutMyself;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public String getPhone() {
        return phone;
    }
    public Gender getGender() {
        return gender;
    }


    // setter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAboutMyself(String aboutMyself) {
        this.aboutMyself = aboutMyself;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
