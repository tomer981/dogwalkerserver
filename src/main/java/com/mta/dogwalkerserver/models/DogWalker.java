package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
////Moreover, we can create our index in the different schema by specifying the schema's name in the name:
//@Index(name = "schema2.fn_index", columnList = "firstName")


@Entity
@Table(name = "dog_walker", schema = "dog_walker_app")
public class DogWalker extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "HOUR_SALARY")
    private int hourSalary;

//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name = "CONTACT_DogOwner", nullable=false)
//    private Set<DogWalker> contact = new HashSet();

    //CONSTRUCTOR
    public DogWalker() {
    }
    public DogWalker(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, String phone, Address address_Id, Gender gender, int hourSalary) {
        super(firstName, lastName, userName, email, aboutMyself, birthDay, phone, address_Id, gender);
        this.hourSalary = hourSalary;
    }

    //getter and setter
    public int getHourSalary() {
        return hourSalary;
    }
    public void setHourSalary(int hourSalary) {
        this.hourSalary = hourSalary;
    }
//    public Set<DogWalker> getContact() {
//        return contact;
//    }
//    public void setContact(Set<DogWalker> contact) {
//        this.contact = contact;
//    }
}
