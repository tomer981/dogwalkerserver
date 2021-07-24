package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;

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


    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    private Address address_Id;

//    https://www.baeldung.com/a-guide-to-java-enums
    @Column(name = "GENDER")
    private Gender gender;//T = male, false = Female.


    public User() {
    }
    public User(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, Address address_Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.aboutMyself = aboutMyself;
        this.birthDay = birthDay;
        this.address_Id = address_Id;
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
}
