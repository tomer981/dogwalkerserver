package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;
////Moreover, we can create our index in the different schema by specifying the schema's name in the name:
//@Index(name = "schema2.fn_index", columnList = "firstName")


//https://www.baeldung.com/java-inheritance
@Entity
@Table(name = "dog_walker", schema="dog_walker_app")
public class DogWalker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    private Address address_Id;

    //CONSTRUCTOR
    public DogWalker() {}
    public DogWalker(String firstName, String lastName, String userName, String aboutMyself, Date birthDay, Address address_Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.aboutMyself = aboutMyself;
        this.birthDay = birthDay;
        this.address_Id = address_Id;
    }

    //GET AND SET
    public Address getAddress_Id() {
        return address_Id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setAddress_Id(Address address) {
        this.address_Id = address;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        this.Id = id;
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

    @Override
    public String toString() {
        return "DogWalker{" +
                "id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address_Id + '\'' +
                '}';
    }
}
