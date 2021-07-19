package com.mta.dogwalkerserver.models;

import javax.persistence.*;
////Moreover, we can create our index in the different schema by specifying the schema's name in the name:
//@Index(name = "schema2.fn_index", columnList = "firstName")

@Entity
@Table(name = "dog_walker", schema="dog_walker_app")
public class DogWalker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    private Address address_Id;

    //CONSTRUCTOR
    public DogWalker() {}

    public DogWalker(String firstName, String lastName, Address address_Id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address_Id = address_Id;
    }

    //GET AND SET
    public Address getAddress_Id() {
        return address_Id;
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
