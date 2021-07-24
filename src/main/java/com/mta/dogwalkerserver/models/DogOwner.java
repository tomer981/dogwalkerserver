package com.mta.dogwalkerserver.models;


import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "dog_owner", schema="dog_walker_app")
public class DogOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    //CONSTRUCTOR
    public DogOwner() {
    }

    public DogOwner(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, Address address_Id) {
        super(firstName, lastName, userName, email, aboutMyself, birthDay, address_Id);
    }
}
