package com.mta.dogwalkerserver.models;


import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dog_owner", schema="dog_walker_app")
public class DogOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dog_id")
    private Dog dog_Id;


//    @OneToMany(cascade=CascadeType.ALL)
//    @JoinColumn(name = "CONTACT_DogWalkers", nullable=false)
//    private Set<DogWalker> contact = new HashSet();

    //CONSTRUCTOR
    public DogOwner() {
    }
    public DogOwner(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, String phone, Address address_Id, Gender gender, Dog dog_Id) {
        super(firstName, lastName, userName, email, aboutMyself, birthDay, phone, address_Id, gender);
        this.dog_Id = dog_Id;
    }

    //getter and setter
    public Dog getDog_Id() {
        return dog_Id;
    }
    public void setDog_Id(Dog dog_Id) {
        this.dog_Id = dog_Id;
    }
}
