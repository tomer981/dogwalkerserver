package com.mta.dogwalkerserver.models;


import javax.persistence.*;

@Entity
@Table(name = "dog", schema="dog_walker_app")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dog_Id")
    private int dog_Id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BREED")
    private String breed;

    @Column(name = "VACCINATED")
    private boolean vaccinated;

    @Column(name = "CASTRATED")
    private boolean castrated;

    @Column(name = "SOCIAL_PEOPLE")
    private boolean socialPeople;

    @Column(name = "SOCIAL_DOG")
    private boolean socialDog;

//    https://www.baeldung.com/a-guide-to-java-enums
//    @Column(name = "GENDER")
//    private boolean gender;//T = male, false = Female.


    //https://www.baeldung.com/spring-controller-return-image-file






}
