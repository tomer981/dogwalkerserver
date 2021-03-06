package com.mta.dogwalkerserver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "dog_walker")
@Table(name = "dog_walker", schema = "dog_walker_app")
@Access(value=AccessType.FIELD)
public class DogWalker extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @Column(name = "HOUR_SALARY")
    private int hourSalary;

    //  https://www.baeldung.com/jackson-ignore-properties-on-serialization
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true )
    @JoinColumn(name = "CONTACT_DOGOWNERS")
    @JsonIgnore
    private Set<DogOwner> contact = new HashSet<DogOwner>();

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_Id")
//    @JsonIgnore
    private Address address_Id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "image")
    private Image image;


    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



    //CONSTRUCTOR
    public DogWalker() {
    }

    public DogWalker(String firstName, String lastName, String userName, String password, String email, String aboutMyself, Date birthDay, String phone, Gender gender, boolean active, String roles, int hourSalary, Set<DogOwner> contact, Address address_Id, Image image) {
        super(firstName, lastName, userName, password, email, aboutMyself, birthDay, phone, gender, active, roles);
        this.hourSalary = hourSalary;
        this.contact = contact;
        this.address_Id = address_Id;
        this.image = image;
    }

    //getter
    public int getId() {
        return Id;
    }
    public int getHourSalary() {
        return hourSalary;
    }
    public Set<DogOwner> getContact() {
        return contact;
    }
    public Address getAddress_Id() {
        return address_Id;
    }
    // public Image getImage_Id() { return image_Id; }

    // setter
    public void setId(int id) {
        Id = id;
    }
    public void setHourSalary(int hourSalary) {
        this.hourSalary = hourSalary;
    }
    public void setContact(Set<DogOwner> contact) {
        this.contact = contact;
    }
    public void setAddress_Id(Address address_Id) {
        this.address_Id = address_Id;
    }
    //  public void setImage_Id(Image image_Id) { this.image_Id = image_Id; }
}
