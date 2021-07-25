package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@MappedSuperclass
public abstract class User {
    @Column(name = "FIRST_NAME",nullable = false)
    private String firstName;

    @Column(name = "LAST_NAME",nullable = false)
    private String lastName;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "ABOUT_MYSELF")
    private String aboutMyself;

    @Column(name = "BIRTH_DAY")
    private Date birthDay;

    @Column(name = "PHONE")
    private String phone;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    private Address address_Id;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER")
    private Gender gender;



    //CONSTRUCTOR
    public User() {
    }
    public User(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, String phone, Address address_Id, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.aboutMyself = aboutMyself;
        this.birthDay = birthDay;
        this.phone = phone;
        this.address_Id = address_Id;
        this.gender = gender;
    }

    //get and setter
    public Gender getGender() {
        return gender;
    }
    public void setGender(Gender gender) {
        this.gender = gender;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAboutMyself() {
        return aboutMyself;
    }
    public void setAboutMyself(String aboutMyself) {
        this.aboutMyself = aboutMyself;
    }
    public Date getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    public Address getAddress_Id() {
        return address_Id;
    }
    public void setAddress_Id(Address address_Id) {
        this.address_Id = address_Id;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
