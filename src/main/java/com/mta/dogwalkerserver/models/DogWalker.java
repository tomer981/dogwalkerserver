package com.mta.dogwalkerserver.models;

import javax.persistence.*;
import java.sql.Date;
////Moreover, we can create our index in the different schema by specifying the schema's name in the name:
//@Index(name = "schema2.fn_index", columnList = "firstName")


@Entity
@Table(name = "dog_walker", schema="dog_walker_app")
public class DogWalker extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;


    //CONSTRUCTOR
    public DogWalker() {}

    public DogWalker(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, Address address_Id) {
        super(firstName, lastName, userName, email, aboutMyself, birthDay, address_Id);
    }
}
