package com.mta.dogwalkerserver.models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "dog_owner")
@Table(name = "dog_owner", schema="dog_walker_app")
//, indexes=@Index(name="index_geo_hash",columnList = "GEO_HASH_LOCATION"))
public class DogOwner extends User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int Id;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dog_id")
    @JsonIgnore
    private Dog dog_Id;

//  https://www.baeldung.com/jackson-ignore-properties-on-serialization
    @OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "CONTACT_DOG_WALKER")
    @JsonIgnore
    private Set<DogWalker> contact = new HashSet<DogWalker>();

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "address_Id")
    @JsonIgnore
    private Address address_Id;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "image_Id")
    @JsonIgnore
    private Image image_Id;

//    @Column(name = "GEO_HASH_LOCATION")
//    @Formula("address_Id.getGeoHashLocation()")
//    private String geoHashLocation;

    //CONSTRUCTOR
    public DogOwner() {
    }
    public DogOwner(String firstName, String lastName, String userName, String email, String aboutMyself, Date birthDay, String phone, Gender gender, Dog dog_Id, Set<DogWalker> contact, Address address_Id) {
        super(firstName, lastName, userName, email, aboutMyself, birthDay, phone, gender);
        this.dog_Id = dog_Id;
        this.contact = contact;
        this.address_Id = address_Id;

    }


    //getter
    public int getId() {
        return Id;
    }
    public Dog getDog_Id() {
        return dog_Id;
    }
    public Set<DogWalker> getContact() {
        return contact;
    }
    public Address getAddress_Id() {
        return address_Id;
    }
    public Image getImage_Id() {
        return image_Id;
    }

    //setter
    public void setId(int id) {
        Id = id;
    }
    public void setDog_Id(Dog dog_Id) {
        this.dog_Id = dog_Id;
    }
    public void setContact(Set<DogWalker> contact) {
        this.contact = contact;
    }
    public void setAddress_Id(Address address_Id) {
        this.address_Id = address_Id;
    }
    public void setImage_Id(Image image_Id) {
        this.image_Id = image_Id;
    }
}
